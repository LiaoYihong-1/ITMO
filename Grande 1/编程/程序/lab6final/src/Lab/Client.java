package Lab;
import Collection.*;
import Command.*;
import Tools.Tools;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class Client {
    public static void main(String[] args) throws IOException,ClassNotFoundException{
        Selector selector = Selector.open();
        //initialize id with file, make sure the property of id

        //create channel
        new CollectionsofPerson().doInitialization();
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);


        //send
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",5555);
        CommandPackage firstset = new CommandPackage("Person.csv",true);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(buffer);
        outputStream.writeObject(firstset);
        channel.send(ByteBuffer.wrap(buffer.toByteArray(), 0, buffer.toByteArray().length), inetSocketAddress);
        outputStream.close();

        channel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);

        boolean print = true;
        set:while(selector.select() > 0){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if(selectionKey.isReadable()){
                    System.out.print("connected\n");
                    System.out.printf("The IP of server is %s\n",inetSocketAddress.getHostString());
                    System.out.printf("The port of server is %d\n",inetSocketAddress.getPort());
                    DatagramChannel datagramChannel = (DatagramChannel) selectionKey.channel();
                    ByteBuffer buffer1 = ByteBuffer.allocate(102400);
                    buffer1.clear();
                    datagramChannel.receive(buffer1);
                    buffer1.flip();
                    ByteArrayInputStream byteArrayInputStream =new ByteArrayInputStream(buffer1.array(),0,buffer1.array().length);
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    Response firstResopne = (Response) objectInputStream.readObject();
                    System.out.print(firstResopne.getManagerout());
                    CollectionsofPerson.setPeople(firstResopne.getPeople());
                    objectInputStream.close();
                    selectionKey.interestOps(SelectionKey.OP_WRITE);
                    break set;
                }else if(!selectionKey.isConnectable()){
                    if(print) {
                        System.out.print("connecting server...\n");
                        print = false;
                    }
                    channel.send(ByteBuffer.wrap(buffer.toByteArray(), 0, buffer.toByteArray().length), inetSocketAddress);
                }
            }
        }

        for(Person P: new CollectionsofPerson().getPeople()){
            if(P.getId()>Person.getidcode()){
                Person.setIdcode(P.getId());
            }
        }

        CommandManager commandManager = new CommandManager();
        boolean print1 = true;
        while (true) {
            if (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isWritable()) {
                        print1 = true;
                        System.out.print("input you command:\n");
                        String[] commarg = Tools.input().split(" ");
                        //make sure command exists
                        boolean exist = false;
                        AbstractCommand command;
                        AbstractCommand abstractCommand;
                        CommandPackage commandPackage = null;
                        Iterator<AbstractCommand> commandIterator = new CommandManager().getCommands().iterator();
                        while (commandIterator.hasNext()) {
                            if ((abstractCommand = commandIterator.next()).getName().equalsIgnoreCase(commarg[0])) {
                                command = abstractCommand;
                                exist = true;
                                //initialize commandPackage
                                try {
                                    //Packing different command and their parameters
                                    if (!command.getName().equalsIgnoreCase("executeScript")) {
                                        DatagramChannel datagramChannel = (DatagramChannel) key.channel();
                                        commandPackage = packcommand(commarg, commandManager, commandPackage, command,"Person.csv");
                                        ByteArrayOutputStream BAO = new ByteArrayOutputStream();
                                        ObjectOutputStream OS = new ObjectOutputStream(BAO);
                                        OS.writeObject(commandPackage);
                                        datagramChannel.send(ByteBuffer.wrap(BAO.toByteArray(), 0, BAO.toByteArray().length), inetSocketAddress);
                                        OS.close();
                                        key.interestOps(SelectionKey.OP_READ);
                                    }else{
                                        DatagramChannel datagramChannel =(DatagramChannel) key.channel();
                                        List<CommandPackage> packageList = new ArrayList<>();
                                        List<AbstractCommand> commandList = new ArrayList<>();
                                        List<String> commandnameList = new ArrayList<>();
                                        forscript(commarg,commandnameList);
                                        Iterator<String> iterator1 = commandnameList.iterator();
                                        while(iterator1.hasNext()){
                                            AbstractCommand abstractCommand1 ;
                                            String S = iterator1.next();
                                            Iterator<AbstractCommand> abstractCommandIterator = commandManager.getCommands().iterator();
                                            while(abstractCommandIterator.hasNext()){
                                                if((abstractCommand1=abstractCommandIterator.next()).getName().equalsIgnoreCase(S.split(" ")[0])){
                                                    commandList.add(abstractCommand1);
                                                }
                                            }
                                        }
                                        Iterator<AbstractCommand> commandIterator1 = commandList.iterator();
                                        Iterator<String> stringIterator = commandnameList.iterator();
                                        while(commandIterator1.hasNext()){
                                            packageList.add(packcommand(stringIterator.next().split(" "),commandManager,new CommandPackage(),commandIterator1.next(),"Person.csv"));
                                        }
                                        CommandPackage finalone =new CommandPackage(packageList);
                                        ByteArrayOutputStream bao = new ByteArrayOutputStream();
                                        ObjectOutputStream oos = new ObjectOutputStream(bao);
                                        oos.writeObject(finalone);
                                        datagramChannel.send(ByteBuffer.wrap(bao.toByteArray(),0,bao.toByteArray().length),inetSocketAddress);
                                        oos.close();
                                        key.interestOps(SelectionKey.OP_READ);
                                    }
                                } catch (ParaInapproException | ValueTooSmallException | ValueTooBigException | NullException P)/*when parameter more than one,because even the commands with parameter only accept one para*/ {
                                    System.out.print(P.getMessage());
                                } catch (NumberFormatException N) {
                                    System.out.print("Next time remember enter a number\n");
                                } catch (IllegalArgumentException I) {
                                    System.out.print("remember enter a color,which can be found in the list\n");
                                }catch (FileNotFoundException F){
                                    System.out.print(F.getMessage()+"\n");
                                }
                            }
                        }
                        if(!exist){
                            System.out.print("No such a command, try again\n");
                        }
                    } else if (key.isReadable()) {
                        DatagramChannel datagramChannel = (DatagramChannel) key.channel();
                        ByteBuffer buffer1 = ByteBuffer.allocate(102400);
                        buffer1.clear();
                        datagramChannel.receive(buffer1);
                        buffer1.flip();
                        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(buffer1.array()));
                        Response response = (Response) objectInputStream.readObject();
                        //String E = new String(buffer1.array(), 0, 4, StandardCharsets.UTF_8);
                        String Print = response.getManagerout();
                        Person.setIdcode(response.getId());
                        if (Print.equalsIgnoreCase("Exit")) {
                            channel.close();
                            System.exit(2);
                        } else {
                            System.out.print(Print);
                        }
                        key.interestOps(SelectionKey.OP_WRITE);
                    }
                }
            }
        }
    }

    public static CommandPackage packcommand(String [] commarg,CommandManager commandManager,CommandPackage commandPackage,AbstractCommand command,String FileName) throws IOException,ParaInapproException{
        if (commarg.length == 2) {
            String name = command.getName();
            //all command don't accept any para
            if (name.equals(new Add().getName()) || name.equals(new Addifmin().getName()) || name.equals(new Average().getName()) || name.equals(new Clear().getName()) || name.equals(new Exit().getName()) || name.equals(new Help().getName()) || name.equals(new History().getName()) || name.equals(new Info().getName()) || name.equals(new Print().getName()) || name.equals(new Show().getName()) || name.equals(new Save().getName())) {
                throw new ParaInapproException("this command don't accept parameter\n");
            }
            //for command Removebyid
            if (commarg[0].equalsIgnoreCase("Removebyid")) {
                try {
                    Person P = commandManager.findByid(Integer.valueOf(commarg[1]));
                    commandPackage = new CommandPackage(command, P,FileName);
                } catch (ParaInapproException P) {
                    System.out.print(P.getMessage());
                }
            }//for command Removegreater
            else if (commarg[0].equalsIgnoreCase("Removegreater")) {
                Person P = commandManager.findByid(Integer.valueOf(commarg[1]));
                commandPackage = new CommandPackage(command, P,FileName);
            }//for command Removebyeyecolor
            else if (commarg[0].equalsIgnoreCase("removeeyecolor")) {
                LinkedHashSet<Person> linkedHashSet = commandManager.findbyEye(commarg[1]);
                commandPackage = new CommandPackage(command, linkedHashSet,FileName);
            } else if (commarg[0].equalsIgnoreCase("UpdateID")) {
                new CollectionsofPerson().doInitialization();
                if(new CollectionsofPerson().getPeople().size() == 0){
                    throw new NullException("Empty collection\n");
                }
                Person after = Person.peoplecreate();
                Person.balaceicode();
                String[] I = {commarg[1]};
                commandPackage = new CommandPackage(I, command, after,FileName);
            }else if(commarg[0].equalsIgnoreCase("Executescript")){
                File F = new File(commarg[1]);
                if(!F.exists()){
                    throw new FileNotFoundException("no such a file,choose a available script please\n");
                }else {
                    commandPackage = new CommandPackage(commarg,command,FileName);
                }
            }
            else {
                commandPackage = new CommandPackage(commarg, command,FileName);
            }
        } else if (commarg.length == 1) {
            //all commands that accept one parameter
            String [] acceptone = {"Removegreater","Removebyid","updateid","removeeyecolor"};
            for(String S:acceptone){
                if(S.equalsIgnoreCase(commarg[0])){
                    throw new ParaInapproException("this command accept 1 parameter\n");
                }
            }
            if (commarg[0].equalsIgnoreCase("add") || commarg[0].equalsIgnoreCase("addifmin")) {
                Person P = Person.peoplecreate();
                commandPackage = new CommandPackage(command, P,FileName);
            } else {
                commandPackage = new CommandPackage(commarg, command,FileName);
            }
        } else {
            throw new ParaInapproException("Commands only accept one parameter\n");
        }
        return commandPackage;
    }

    /**
     * this command is set for analysing script. It will open all script and create a list of all commands in scripts.
     * @param commarg String [],
     * @param commandnameList Arraylist<String>, where save list
     * @return
     * @throws IOException
     */
    public static List<String> forscript(String [] commarg,List<String> commandnameList)throws IOException{
        commandnameList.add(commarg[0]+" "+commarg[1]);
        FileReader fileReader = new FileReader(new File(commarg[1]));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s;
        boolean found = false;
        while((s = bufferedReader.readLine())!=null){
            String [] judge = s.split(" ");
            if(judge.length>=3){
                throw new ParaInapproException("Make sure that your commands in a proper format\n");
            }
            Iterator<AbstractCommand> iterator1 = new CommandManager().getCommands().iterator();
            while(iterator1.hasNext()){
                if((iterator1.next()).getName().equalsIgnoreCase(judge[0])){
                    found = true;
                    break;
                }
            }
            if(!found){
                throw new NullException("Make sure that every command is available in your script\n");
            }
            if(!judge[0].equalsIgnoreCase("executescript")) {
                commandnameList.add(s);
            }else if(!judge[1].equals(commarg[1])){
                forscript(judge,commandnameList);
            }
        }
        return commandnameList;
    }
}
