package Frames;

import Collection.*;
import Command.*;
import Lab.*;
import Tools.Tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.*;
import java.util.List;

public class Worker extends JFrame {
    protected static JTextArea area;
    private JPanel commandP;
    private JTextField field;
    private CommandManager commandManager = new CommandManager();
    protected static CollectionsofPerson collection = null;

    public Worker(Basic basic, ResourceBundle resourceBundle){
        this.collection = basic.getCollection();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(800,300,750,600);
        FlowLayout f = new FlowLayout();
        f.setVgap(50);
        setLayout(f);
        setResizable(false);
        Container container = getContentPane();

        //panel of command
        commandP = new JPanel();
        commandP.setBounds(70,50,500,50);
        commandP.setVisible(true);
        //label of commands
        JLabel label = new JLabel("Command:");
        label.setVisible(true);
        //text of command
        field = new JTextField("Input your command here",15);
        field.setSize(600,50);
        field.setVisible(true);
        field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    heart(basic);
                    field.setText("");
                }catch (Exception c){
                    area.append(c.getMessage());
                }
            }
        });
        //add
        commandP.add(label);
        commandP.add(field);
        container.add(commandP);

        //show result
        area = new JTextArea(25,65);
        area.setText("Result:\n");
        area.setEditable(false);
        area.setVisible(true);
        //panel of result
        JScrollPane result = new JScrollPane(area);
        //add
        result.setVisible(true);
        container.add(result);

        if(resourceBundle != null){
            area.setText(resourceBundle.getString("Result")+":"+"\n");
            label.setText(resourceBundle.getString("Command")+":");
            field.setText(resourceBundle.getString("Tip"));
        }
        setVisible(true);
    }

    public JTextArea getArea() {
        return area;
    }

    private byte[] serialization(Object o) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(o);
        byte [] data = byteArrayOutputStream.toByteArray();
        outputStream.close();
        return data;
    }

    private List<String> forscript(String [] commarg, List<String> commandnameList)throws IOException{
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


    private CommandPackage packcommand(String [] commarg,CommandManager commandManager,CommandPackage commandPackage,AbstractCommand command,CollectionsofPerson collection,String username) throws IOException,ParaInapproException{
        if (commarg.length == 2) {
            String name = command.getName();
            //all command don't accept any para
            if (name.equals(new Add().getName()) || name.equals(new Addifmin().getName()) || name.equals(new Average().getName()) || name.equals(new Clear().getName()) || name.equals(new Exit().getName()) || name.equals(new Help().getName()) || name.equals(new History().getName()) || name.equals(new Info().getName()) || name.equals(new Print().getName()) || name.equals(new Show().getName())) {
                throw new ParaInapproException("this command don't accept parameter\n");
            }
            //for command Removebyid
            if (commarg[0].equalsIgnoreCase("Removebyid")) {
                try {
                    Person P = commandManager.findByid(Integer.valueOf(commarg[1]),collection);
                    commandPackage = new CommandPackage(command, P);
                } catch (ParaInapproException P) {
                    System.out.print(P.getMessage());
                }
            }//for command Removegreater
            else if (commarg[0].equalsIgnoreCase("Removegreater")) {
                Person P = commandManager.findByid(Integer.valueOf(commarg[1]),collection);
                commandPackage = new CommandPackage(command, P);
            }//for command Removebyeyecolor
            else if (commarg[0].equalsIgnoreCase("removeeyecolor")) {
                commandPackage = new CommandPackage(commarg,command);
            } else if (commarg[0].equalsIgnoreCase("UpdateID")) {
                collection.doInitialization();
                if(collection.getPeople().size() == 0){
                    throw new NullException("Empty collection\n");
                }
                Person after = Person.peoplecreate();
                Person.balaceicode();
                after.setHost(username);
                String[] I = {commarg[1]};
                commandPackage = new CommandPackage(I, command, after);
            }else if(commarg[0].equalsIgnoreCase("Executescript")){
                File F = new File(commarg[1]);
                if(!F.exists()){
                    throw new FileNotFoundException("no such a file,choose a available script please\n");
                }else {
                    commandPackage = new CommandPackage(commarg,command);
                }
            }
            else {
                commandPackage = new CommandPackage(commarg, command);
            }
        } else if (commarg.length == 1) {
            //all commands that accept one parameter
            String [] acceptone = {"Removegreater","Removebyid","updateid","removeeyecolor"};
            for(String S:acceptone){
                if(S.equalsIgnoreCase(commarg[0])){
                    throw new ParaInapproException("this command accept 1 parameter\n");
                }
            }
            commandPackage = new CommandPackage(commarg, command);
        } else {
            throw new ParaInapproException("Commands only accept one parameter\n");
        }
        return commandPackage;
    }

    private void heart(Basic basic) throws IOException, ClassNotFoundException{
        all: while (true) {
            if (basic.getSelector().select() > 0) {
                Iterator<SelectionKey> iterator = basic.getSelector().selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isWritable()) {
                        String[] commarg = field.getText().split(" ");
                        //make sure command exists
                        boolean exist = false;
                        AbstractCommand command;
                        AbstractCommand abstractCommand;
                        CommandPackage commandPackage = null;
                        Iterator<AbstractCommand> commandIterator = commandManager.getCommands().iterator();
                        while (commandIterator.hasNext()) {
                            if ((abstractCommand = commandIterator.next()).getName().equalsIgnoreCase(commarg[0])) {
                                command = abstractCommand;
                                exist = true;
                                //initialize commandPackage
                                try {
                                    //Packing different command and their parameters
                                    if (!command.getName().equalsIgnoreCase("executeScript")&&!command.getName().equalsIgnoreCase("add")&&!command.getName().equalsIgnoreCase("addifmin")) {
                                        DatagramChannel datagramChannel = (DatagramChannel) key.channel();
                                        commandPackage = packcommand(commarg,commandManager, commandPackage, command,collection,basic.getUsername());
                                        MainRequest mainRequest = new MainRequest(basic.getClient(),commandPackage);
                                        byte [] data = serialization(mainRequest);
                                        datagramChannel.send(ByteBuffer.wrap(data, 0,data.length), basic.getAddress());
                                        key.interestOps(SelectionKey.OP_READ);
                                    }else if(command.getName().equalsIgnoreCase("add")||command.getName().equalsIgnoreCase("addifmin")){
                                        new CreatePersonFrame("add",basic,command);
                                        key.interestOps(SelectionKey.OP_WRITE);
                                        break all;
                                    }
                                    else{
                                        //for executescript
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
                                            packageList.add(packcommand(stringIterator.next().split(" "),commandManager,new CommandPackage(),commandIterator1.next(),collection,basic.getUsername()));
                                        }
                                        CommandPackage finalone =new CommandPackage(packageList);
                                        MainRequest mainRequest = new MainRequest(basic.getClient(),finalone);
                                        byte[] data=serialization(mainRequest);
                                        datagramChannel.send(ByteBuffer.wrap(data,0,data.length),basic.getAddress());
                                        key.interestOps(SelectionKey.OP_READ);
                                    }
                                } catch (ParaInapproException | ValueTooSmallException | ValueTooBigException | NullException P)/*when parameter more than one,because even the commands with parameter only accept one para*/ {
                                    area.append(P.getMessage());
                                } catch (NumberFormatException N) {
                                    area.append("Next time remember enter a number\n");
                                } catch (IllegalArgumentException I) {
                                    area.append("remember enter a color,which can be found in the list\n");
                                }catch (FileNotFoundException F){
                                    area.append(F.getMessage()+"\n");
                                }
                            }
                        }
                        if(!exist){
                            area.append("No such a command, try again\n");
                            key.interestOps(SelectionKey.OP_WRITE);
                            break all;
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
                        collection.setPeople(response.getPeople());
                        if (Print.equalsIgnoreCase("Exit")) {
                            basic.close();
                            System.exit(2);
                        } else {
                            area.append(Print);
                            key.interestOps(SelectionKey.OP_WRITE);
                            break all;
                        }
                    }
                }
            }
        }
    }
}
