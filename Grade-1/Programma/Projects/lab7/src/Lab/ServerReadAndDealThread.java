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
                        }catch(SQLException|NullException S){
                            System.out.print(S.getMessage());
                            manager.setOut(S.getMessage()+"\n",false);
                        }
                        if (command.getName().equalsIgnoreCase("updateid")) {
                            collection.setPeople(Server.sort(collection.getPeople()));
                        }
                    }
                    this.response = new Response(Person.getidcode(),collection.getPeople() ,manager.getOut());
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
                    String sq = "jdbc:postgresql://" + information.getIp() + ":" + information.getPort() + "/" + information.getDatabase();

                    //register
                    if(request.getCilentInformation().isCreate()) {
                        if(userExist(information.getUser())){
                            throw new SQLException("This username is existed\n");
                        }else {
                            addUser(information.getUser(), information.getHash());
                        }
                    }

                    //login
                    if(!accountExist(information.getUser(),information.getHash())){
                        manager.setOut("Your username or password improper\n",false);
                        this.response = new Response(Person.getidcode(), collection.getPeople(), manager.getOut());
                        response.setException(true);
                    }else {
                        try (Connection connection = DriverManager.getConnection(sq, "s291007", "pgt813")) {
                            try (Statement ps = connection.createStatement()) {
                                try (ResultSet rs = ps.executeQuery("SELECT id,name,haircolor,eyecolor,height,location,x,y,z,creationdate,birthday,username FROM people ORDER BY id ASC")) {
                                    collection.clear();
                                    while (rs.next()) {
                                        int id = rs.getInt("id");
                                        String name = rs.getString("name");
                                        String haircolor = rs.getString("haircolor");
                                        String eyecolor = rs.getString("eyecolor");
                                        int height = rs.getInt("height");
                                        String location = rs.getString("location");
                                        int x = rs.getInt("x");
                                        int y = (int) rs.getDouble("y");
                                        int z = rs.getInt("z");
                                        String creationdate = rs.getString("creationdate");
                                        String birthday = rs.getString("birthday");
                                        String host = rs.getString("username");
                                        Person p = new Person(new Location(new Coordinates(x, y), location, z), HairColor.valueOf(haircolor), EyeColor.valueOf(eyecolor), name, height);
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
                        this.response = new Response(Person.getidcode(), collection.getPeople(), manager.getOut());
                    }
                } catch (SQLException S){
                    S.printStackTrace();
                    manager.setOut(S.getMessage(),false);
                    this.response = new Response(Person.getidcode(),collection.getPeople(),manager.getOut());
                    response.setException(true);
                }

            }
        }catch (ClassNotFoundException | IOException C){
            C.printStackTrace();
        }
    }

    public boolean userExist(String username) throws SQLException{
        String get = null;
        String sq = "jdbc:postgresql://" + information.getIp() + ":" + information.getPort() + "/" + information.getDatabase();
        try (Connection connection = DriverManager.getConnection(sq,"s291007", "pgt813")) {
            try (Statement ps = connection.createStatement()) {
                try (ResultSet rs = ps.executeQuery("SELECT username,password FROM users where user = " + "'" + username + "'")) {
                    if(rs.next()) {
                        get = rs.getString("username");
                    }
                }
            }
        }
        if(get!=null) {
            return true;
        }else {
            return false;
        }
    }

    public boolean accountExist(String username,String password) throws SQLException{
        boolean exist = false;
        String sq = "jdbc:postgresql://" + information.getIp() + ":" + information.getPort() + "/" + information.getDatabase();
        try (Connection connection = DriverManager.getConnection(sq,"s291007", "pgt813")) {
            try (Statement ps = connection.createStatement()) {
                try (ResultSet rs = ps.executeQuery("SELECT username,password FROM users where username = "+ "'" + username +"'" + " and password = "+ "'" + password + "'")) {
                    if(rs.next()){
                        exist = true;
                    }
                }
            }
        }
        return exist;
    }


    public void addUser(String name,String password) throws SQLException{
        String sq = "jdbc:postgresql://" + information.getIp() + ":" + information.getPort() + "/" + information.getDatabase();
        try (Connection connection = DriverManager.getConnection(sq,"s291007", "pgt813")) {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO users (username,password) values (?,?)")) {
                ps.setObject(1,name);
                ps.setObject(2,password);
                ps.executeUpdate();
            }
        }

    }
}
