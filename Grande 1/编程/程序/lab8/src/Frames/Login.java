package Frames;

import Collection.CollectionsofPerson;
import Lab.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;

public class Login extends JFrame {
    private JTextField user;
    private JPasswordField passwordField;
    private Basic basic ;
    private JLabel username;
    Locale locale = null;
    ResourceBundle resourceBundle = null;
    JButton button1;
    JButton button;
    JLabel password;

    public Login(Basic basic){
        //init
        Login login = this;
        this.basic = basic;
        boolean print = true;
        setBounds(800,350,500,350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Container
        Container container = this.getContentPane();
        container.setLayout(null);

        //Button for changing language
        

        //
        JComboBox language = new JComboBox();
        language.addItem("Russian");
        language.addItem("Bulgarian");
        language.addItem("Macedonian");
        language.addItem("English(Australia)");
        language.setBackground(Color.WHITE);
        language.setBounds(200,0,100,30);
        language.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    String text =(String) e.getItem();
                    locale = changeLocale(text);
                    resourceBundle = ResourceBundle.getBundle("resource.resource",locale);
                    username.setText(resourceBundle.getString("Name") + ":");
                    password.setText(resourceBundle.getString("Password") + ":");
                    button.setText(resourceBundle.getString("Register"));
                    button1.setText(resourceBundle.getString("Login"));
                }
            }
        });
        container.add(language);
        //button for register
        button = new JButton("Register");
        button.setBounds(300,250,100,35);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register(basic,login,resourceBundle);
            }
        });
        button.setVisible(true);
        container.add(button);

        //button for login
        button1  = new JButton("Login");
        button1.setBounds(100,250,100,35);
        button1.setVisible(true);
        container.add(button1);


        //username
        username = new JLabel("User:");
        username.setBounds(125,70,50,30);
        container.add(username,BorderLayout.WEST);
        username.setVisible(true);

        //username text
        user = new JTextField(15);
        user.setBounds(175,70,150,30);
        container.add(user,BorderLayout.EAST);
        username.setVisible(true);

        //password
        password = new JLabel("Password:");
        password.setBounds(100,130,70,30);
        container.add(password);
        password.setVisible(true);

        //password text
        passwordField = new JPasswordField(20);
        passwordField.setBounds(175,130,150,30);
        passwordField.setEchoChar('*');
        passwordField.setVisible(true);
        container.add(passwordField);

        //button login
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    connect(print);
                }catch (ClassNotFoundException | IOException E){
                    new Mistake(E.getMessage());
                }
            }
        });

        setVisible(true);
        setResizable(false);
    }


    public String getUser(){
        return this.user.getText();
    }

    public String getPassword(){
        return String.valueOf(this.passwordField.getPassword());
    }

    private void connect(boolean print) throws IOException, ClassNotFoundException{
        Selector selector = basic.getSelector();
        InetSocketAddress inetSocketAddress = basic.getAddress();
        CollectionsofPerson collection = basic.getCollection();
        DatagramChannel channel = basic.getChannel();
        basic.setClient(new ClientInformation(getUser(),getPassword(),false));
        MainRequest request =new MainRequest(basic.getClient());
        byte [] send = serialization(request);
        channel.send(ByteBuffer.wrap(send,0,send.length),inetSocketAddress);
        set:while(selector.select() > 0 ){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if(selectionKey.isReadable()){
                    DatagramChannel datagramChannel = (DatagramChannel) selectionKey.channel();
                    ByteBuffer buffer1 = ByteBuffer.allocate(102400);
                    buffer1.clear();
                    datagramChannel.receive(buffer1);
                    buffer1.flip();
                    ByteArrayInputStream byteArrayInputStream =new ByteArrayInputStream(buffer1.array(),0,buffer1.array().length);
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    Response firstResopne = (Response) objectInputStream.readObject();
                    System.out.print(firstResopne.getManagerout());
                    collection.setPeople(firstResopne.getPeople());
                    objectInputStream.close();
                    collection.setPeople(firstResopne.getPeople());
                    if(firstResopne.isException()){
                        selectionKey.interestOps(SelectionKey.OP_READ);
                        clear();
                        break set;
                    }else {
                        selectionKey.interestOps(SelectionKey.OP_WRITE);
                        basic.setUsername(getUser());
                        this.dispose();
                        new Worker(basic,resourceBundle);
                        break set;
                    }
                }else if(!selectionKey.isConnectable()){
                    if(print) {
                        System.out.print("connecting server...\n");
                        print = false;
                    }
                }
            }
        }
    }

    private Locale changeLocale(String text){
        if(text.equals("Bulgarian")){
            this.locale = new Locale("bg","BG");
        }else if (text.equals("Russian")){
            this.locale = new Locale("ru","RU");
        }else if(text.equals("English(Australia)")){
            this.locale = new Locale("en","AU");
        }else {
            this.locale = new Locale("mk","MK");
        }
        return locale;
    }

    private void clear(){
        user.setText("");
        passwordField.setText("");
    }

    public Basic getBasic() {
        return basic;
    }

    private byte[] serialization(Object o) throws IOException {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
                outputStream.writeObject(o);
                byte [] data = byteArrayOutputStream.toByteArray();
                outputStream.close();
                return data;
            }
}
