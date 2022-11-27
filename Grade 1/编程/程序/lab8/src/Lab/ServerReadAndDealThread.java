package Lab;

import Collection.*;
import Command.AbstractCommand;
import Command.CommandManager;
import Command.History;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class ServerReadAndDealThread extends Thread{
    private MainRequest request;
    private final CollectionsofPerson collection;
    private Response response;
    private final DatagramSocket socket;
    private DatagramPacket packet;
    private final CommandManager manager;
    private ClientInformation information;
    private ObjectInputStream inputStream;
    private boolean exit = false;
    private final LinkedHashSet<Integer> ports;

    ServerReadAndDealThread(CollectionsofPerson collection, DatagramSocket socket,LinkedHashSet<Integer> ports){
        this.request = null;
        this.collection = collection;
        this.manager = new CommandManager();
        this.packet = null;
        this.ports = ports;
        this.response = null;
        this.information = null;
        this.socket=socket;
        this.inputStream = null;
    }

    public CollectionsofPerson getCollection() {
        return collection;
    }

    public Response getResponse() {
        return response;
    }

    public boolean isExit() {
        return exit;
    }

    public LinkedHashSet<Integer> getPorts() {
        return ports;
    }

    public DatagramPacket getPacket() {
        return packet;
    }

    @Override
    public void run() {
        //recieve
        try {
            byte[] buffer = new byte[102400];
            packet = new DatagramPacket(buffer, 0, buffer.length);
            socket.receive(packet);
            inputStream = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
        }catch (Exception e){
            e.printStackTrace();
        }

        //
        try{
            request = (MainRequest) inputStream.readObject();
            if(request.getCommandPackage()!=null){
                CommandPackage commandPackage = request.getCommandPackage();
                if (commandPackage.getList() == null & !commandPackage.isSet()) {
                    AbstractCommand command = commandPackage.getAbstractCommand();
                    new History().getHistory().add(command.getName() + "\n");
                    if (command.getName().equalsIgnoreCase("exit")) {
                        try {
                            command.execute(manager, request, collection);
                        }catch (SQLException S){
                            S.printStackTrace();
                        }
                        this.exit = true;

                        this.response = new Response(Person.getidcode(), manager.getOut());
                        for(Integer ip: ports){
                            if(ip.equals(packet.getPort())){
                                ports.remove(ip);
                            }
                        }
                        //System.exit(2);
                    } else {
                        try {
                            command.execute(manager, request, collection);
                        }catch(SQLException S){
                            S.printStackTrace();
                            System.out.print(S.getMessage());
                            manager.setOut(S.getMessage()+"\n",false);
                        }
                        if (command.getName().equalsIgnoreCase("updateid")) {
                            collection.setPeople(Server.sort(collection.getPeople()));
                        }
                    }
                    this.response = new Response(Person.getidcode(),collection.getPeople() ,manager.getOut());
                    /*
                    ByteArrayOutputStream bao = new ByteArrayOutputStream();
                    ObjectOutputStream bos = new ObjectOutputStream(bao);
                    bos.writeObject(response);
                    byte[] data = bao.toByteArray();//manager.getOut().getBytes();
                    DatagramPacket send = new DatagramPacket(data, data.length, packet.getSocketAddress());
                    socket.send(send);
                    System.out.print(manager.getOut());
                    System.out.print("\n");
                    bos.close();
                    */
                    //for command executescript
                } else if (!commandPackage.isSet()) {
                    StringBuilder S = new StringBuilder();
                    List<CommandPackage> list = commandPackage.getList();
                    Iterator<CommandPackage> iterator = list.iterator();
                    try {
                        while (iterator.hasNext()) {
                            CommandPackage used = iterator.next();
                            AbstractCommand command = used.getAbstractCommand();
                            new History().getHistory().add(command.getName() + "\n");
                            MainRequest forscript = new MainRequest(used);
                            command.execute(manager, forscript, collection);
                            S.append(manager.getOut()).append("\n");
                        }
                        manager.setOut(S.toString(), false);
                    }catch (SQLException s){
                        System.out.print(s.getMessage());
                        manager.setOut(s.getMessage()+"\n",false);
                    }
                    this.response = new Response(Person.getidcode(), manager.getOut());
                }
            }else if(request.getCilentInformation()!=null){
                //connection to database and read it
                System.out.printf("The port of client is %d\n", packet.getPort());
                Integer newport = packet.getPort();
                try {
                    ports.add(newport);
                    information = request.getCilentInformation();
                    Class.forName("org.postgresql.Driver");
                    //String sq = "jdbc:postgresql://" + "pg" + ":" + "5432" + "/" + "studs";
                    String sq = "jdbc:postgresql://" + information.getIp() + ":" + information.getPort() + "/" + "studs";
                    if(request.getCilentInformation().isCreate()) {
                        try (Connection connection = DriverManager.getConnection(sq, "postgres", "123456")) {
                            try (PreparedStatement ps = connection.prepareStatement("CREATE USER " + information.getUser() + " WITH PASSWORD " + "'" + information.getPassword()+ "'")) {
                                ps.executeUpdate();
                                try (PreparedStatement ps1 = connection.prepareStatement("GRANT ALL ON " + "people" + " TO " + information.getUser())) {
                                    ps1.executeUpdate();
                                    try(PreparedStatement ps2 = connection.prepareStatement("GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO " + information.getUser())){
                                        ps2.executeUpdate();
                                    }
                                }
                            }
                        }
                    }
                    try (Connection connection = DriverManager.getConnection(sq,information.getUser(), information.getPassword())) {
                        try (Statement ps = connection.createStatement()){
                            try(ResultSet rs = ps.executeQuery("SELECT id,name,haircolor,eyecolor,height,location,x,y,z,creationdate,birthday,username FROM people")){
                                collection.clear();
                                while(rs.next()){
                                    int id = rs.getInt("id");
                                    String name = rs.getString("name");
                                    String haircolor = rs.getString("haircolor");
                                    String eyecolor = rs.getString("eyecolor");
                                    int height = rs.getInt("height");
                                    String location = rs.getString("location");
                                    int x = rs.getInt("x");
                                    int y =(int) rs.getDouble("y");
                                    int z = rs.getInt("z");
                                    String creationdate = rs.getString("creationdate");
                                    String birthday = rs.getString("birthday");
                                    String host = rs.getString("username");
                                    Person p = new Person(new Location(new Coordinates(x,y),location,z), HairColor.valueOf(haircolor),EyeColor.valueOf(eyecolor),name,height);
                                    p.setHost(host);
                                    p.resetid(id);
                                    p.setBirthday(ZonedDateTime.parse(birthday));
                                    p.setCreationDate(LocalDate.parse(creationdate));
                                    Person.setIdcode(id);
                                    collection.add(p);
                                }
                            }
                        }
                    }
                    manager.setOut("success\n", false);
                    this.response = new Response(Person.getidcode(),collection.getPeople(),manager.getOut());
                    /*
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
                    outputStream.writeObject(response);
                    byte[] data = byteArrayOutputStream.toByteArray();
                    DatagramPacket send = new DatagramPacket(data, data.length, packet.getSocketAddress());
                    socket.send(send);
                    outputStream.close();
                    */
                } catch ( SQLException S){
                    S.printStackTrace();
                    ports.remove(newport);
                    if(information.isCreate()){
                        manager.setOut("This user name is existed, reset new name\n",false);
                    }else {
                        manager.setOut("There are two possibilities:\n",false);
                        manager.setOut("1.Your user name or password not proper\n", true);
                        manager.setOut("2.Your account has no enough privilege\n",true);
                        manager.setOut("Just try again or use another account\n",true);
                    }
                    this.response = new Response(Person.getidcode(),collection.getPeople(),manager.getOut());
                    response.setException(true);
                    /*
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
                    outputStream.writeObject(response);
                    byte [] data = byteArrayOutputStream.toByteArray();
                    DatagramPacket send = new DatagramPacket(data,data.length,packet.getSocketAddress());
                    socket.send(send);
                    outputStream.close();
                    */
                }
            }
        }catch (ClassNotFoundException | IOException C){
            System.out.print(C.getMessage());
        }
    }
}
