package Lab;
import CSV.CSVReader;
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
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws IOException,InterruptedException{
        Selector selector = Selector.open();
        //initialize id with file, make sure the property of id

        //create channel
        new CollectionsofPerson().doInitialization();
        DatagramChannel prechannel = DatagramChannel.open();
        prechannel.configureBlocking(false);
        prechannel.bind(new InetSocketAddress("localhost", 8989));

        //prepare
        int idset = 0;
        LinkedHashSet<Person> People = new LinkedHashSet<>();
        new CSVReader().ReadFile(People,"Person.csv");
        CollectionsofPerson.setPeople(People);
        Iterator<Person> preiterator = People.iterator();
        Person preP;
        while(preiterator.hasNext()){
            if(idset <= (preP = preiterator.next()).getId()){
                idset = preP.getId();
            }
        }
        Person.idcode = idset;

        //send
        CommandPackage firstset = new CommandPackage(People,"Person.csv");
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(buffer);
        outputStream.writeObject(firstset);
        prechannel.send(ByteBuffer.wrap(buffer.toByteArray(), 0, buffer.toByteArray().length), new InetSocketAddress("localhost", 5555));
        outputStream.close();

        prechannel.register(selector,SelectionKey.OP_READ);

        loop:while(selector.select() > 0){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isReadable()){
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    prechannel.receive(byteBuffer);
                    System.out.print(new String(byteBuffer.array(),0,byteBuffer.array().length));
                    byteBuffer.clear();
                    prechannel.close();
                    break loop;
                }
            }
        }
        //prechannel.close();


        CommandManager commandManager = new CommandManager();
        while (true) {
            DatagramChannel channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.bind(new InetSocketAddress("localhost", 9090));
            System.out.print("input you command:\n");
            String[] commarg = Tools.Input().split(" ");

        /*
        make sure that command exits
         */
            boolean exist = false;
            AbstractCommand command;
            AbstractCommand abstractCommand;
            CommandPackage commandPackage = null;
            Iterator<AbstractCommand> iterator = new CommandManager().getCommands().iterator();
            try {
                loop:
                while (iterator.hasNext()) {
                    //first initialize command and then commandPackage
                    if ((abstractCommand = iterator.next()).getName().equalsIgnoreCase(commarg[0])) {
                        command = abstractCommand;
                        exist = true;
                        //initialize commandPackage
                        try {
                            //Packing different command and their parameters
                            if(!command.getName().equalsIgnoreCase("executeScript")) {
                                commandPackage = PackCommand(commarg, commandManager, commandPackage, command,channel);
                                //exchange one time
                                communicate(channel,commandPackage);
                            }else{
                                //for command executescript
                                ForScript(commarg,commandPackage,commandManager,channel);
                            }
                        } catch (ParaInapproException| ValueTooSmallException| ValueTooBigException|NullException P)/*when parameter more than one,because even the commands with parameter only accept one para*/ {
                            System.out.print(P.getMessage());
                        }catch(FileNotFoundException F){
                            System.out.print("No this file:\n");
                        }catch (NotInitializationException I ){
                            I.printStackTrace();
                        }catch (NumberFormatException N){
                            System.out.print("Next time remember enter a number\n");
                        }catch (IllegalArgumentException I){
                            System.out.print("remember enter a color,which can be found in the list\n");
                        }
                        //break when find command
                        break loop;
                    }
                }
                if (!exist) {
                    channel.close();
                    throw new ParaInapproException("no such command,try again:\n");
                }
            } catch (ParaInapproException P) {
                System.out.print(P.getMessage());
            }
            channel.close();
        }
    }

    public static CommandPackage PackCommand(String [] commarg,CommandManager commandManager,CommandPackage commandPackage,AbstractCommand command,DatagramChannel channel) throws IOException,ParaInapproException{
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
                    commandPackage = new CommandPackage(command, P,"Person.csv");
                } catch (ParaInapproException P) {
                    System.out.print(P.getMessage());
                }
            }//for command Removegreater
            else if (commarg[0].equalsIgnoreCase("Removegreater")) {
                Person P = commandManager.findByid(Integer.valueOf(commarg[1]));
                commandPackage = new CommandPackage(command, P,"Person.csv");
            }//for command Removebyeyecolor
            else if (commarg[0].equalsIgnoreCase("removeeyecolor")) {
                LinkedHashSet<Person> linkedHashSet = commandManager.findbyEye(commarg[1]);
                commandPackage = new CommandPackage(command, linkedHashSet,"Person.csv");
            } else if (commarg[0].equalsIgnoreCase("UpdateID")) {
                new CollectionsofPerson().doInitialization();
                if(new CollectionsofPerson().getPeople().size() == 0){
                    throw new NullException("Empty collection\n");
                }
                Person after = Person.PeopleCreate();
                Person.balaceicode();
                String[] I = {commarg[1]};
                commandPackage = new CommandPackage(I, command, after,"Person.csv");
            }else if(commarg[0].equalsIgnoreCase("Executescript")){
                ForScript(commarg,commandPackage,commandManager,channel);
            }
            else {
                commandPackage = new CommandPackage(commarg, command,"Person.csv");
            }
        } else if (commarg.length == 1) {
            //for command Add and Addifmin
            if(commarg[0].equalsIgnoreCase("Removegreater")||commarg[0].equalsIgnoreCase("Removebyid")||commarg[0].equalsIgnoreCase("updateid")||commarg[0].equalsIgnoreCase("removeeyecolor")){
                throw new ParaInapproException("this command accept 1 parameter\n");
            } else if (commarg[0].equalsIgnoreCase("add") || commarg[0].equalsIgnoreCase("addifmin")) {
                Person P = Person.PeopleCreate();
                commandPackage = new CommandPackage(command, P,"Person.csv");
            } else {
                commandPackage = new CommandPackage(commarg, command,"Person.csv");
            }
        } else {
            throw new ParaInapproException("Commands only accept one parameter\n");
        }
        return commandPackage;
    }

    public static void ForScript(String [] commarg, CommandPackage commandPackage, CommandManager commandManager, DatagramChannel channel) throws IOException{
        //for command executescript
        if(commarg.length>2){
            throw new ParaInapproException("all commands can't receive more than one parameter\n");
        }else{
            commandPackage = new CommandPackage(commarg,new ExecuteScript(),"Person.csv");
            communicate(channel,commandPackage);
            File F = new File(commarg[1]);
            FileReader f=new FileReader(F);
            BufferedReader bufferedReader=new BufferedReader(f);
            String commandtext="";
            while((commandtext=bufferedReader.readLine())!=null){
                AbstractCommand abstractCommand1;
                AbstractCommand used;
                String []split=commandtext.split(" ");
                if(split.length>3){
                    throw new ParaInapproException("Make sure that every command contains correct amount of parameters\n");
                }
                Iterator<AbstractCommand> iterator1 = new CommandManager().getCommands().iterator();
                boolean B = false;
                while(iterator1.hasNext()){
                    if((abstractCommand1 = iterator1.next()).getName().equalsIgnoreCase(split[0])){
                        used = abstractCommand1;
                        commandPackage = PackCommand(split,commandManager,commandPackage,used,channel);
                        B = true;
                    }
                }
                if(!B){
                    throw new NullException("Please make sure that every name of command in your file proper\n");
                }
                communicate(channel,commandPackage);
            }
            bufferedReader.close();
        }
    }

    public static String communicate(DatagramChannel channel,CommandPackage commandPackage) throws IOException{
        //sent byte after serialization
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(buffer);
        outputStream.writeObject(commandPackage);
        channel.send(ByteBuffer.wrap(buffer.toByteArray(), 0, buffer.toByteArray().length), new InetSocketAddress("localhost", 5555));
        outputStream.close();
        ByteBuffer buffer1 = ByteBuffer.allocate(2048);
        buffer1.clear();
        channel.receive(buffer1);
        String E = new String(buffer1.array(),0,4, StandardCharsets.UTF_8);
        String Print = new String (buffer1.array());
        if(E.equals("Exit")){
            System.exit(2);
        }else {
            System.out.print(Print);
        }
        return Print;
    }

    /**
     * deal with id code of person, make sure id code will be the biggest
     * @throws IOException is created by{@link CSVReader#ReadFile(LinkedHashSet, String)}
     */
    /*private static void setting() throws IOException{
        new CollectionsofPerson().doInitialization();
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.bind(new InetSocketAddress("localhost", 8989));
        int idset = 0;
        Person.idcode = idset;
        LinkedHashSet<Person> People = new LinkedHashSet<>();
        new CSVReader().ReadFile(People,"Person.csv");
        CollectionsofPerson.setPeople(People);
        communicate(channel,new CommandPackage(People,"Person.csv"));
        Iterator<Person> iterator = People.iterator();
        Person P;
        while(iterator.hasNext()){
            if(idset <= (P = iterator.next()).getId()){
                idset = P.getId();
            }
        }
        Person.idcode = idset;
        channel.close();
    }*/

}