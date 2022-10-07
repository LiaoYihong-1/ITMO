package Frames;

import Collection.CollectionsofPerson;
import Lab.Basic;
import Lab.ClientInformation;
import Lab.MainRequest;
import Lab.Response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImagingOpException;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Register extends JDialog {
    private JTextField user;
    private JPasswordField passwordField;
    private Basic basic;
    private boolean print = true;
    private Login login;
    private ResourceBundle resourceBundle = null;

    Register(Basic basic, Login login, ResourceBundle resourceBundle){
        this.basic = basic;
        this.login = login;
        this.resourceBundle = resourceBundle;
        setBounds(800,350,500,350);
        Container container = getContentPane();
        setLayout(null);

        //button for register
        JButton button  = new JButton("Done");
        button.setBounds(200,250,100,35);
        button.setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                register();
            }
        });
        container.add(button);

        //username
        JLabel username = new JLabel("User:");
        username.setBounds(125,70,50,30);
        container.add(username,BorderLayout.WEST);
        username.setVisible(true);

        //username text
        user = new JTextField(15);
        user.setBounds(175,70,150,30);
        container.add(user,BorderLayout.EAST);
        user.setVisible(true);

        //password
        JLabel password = new JLabel("Password:");
        password.setBounds(100,130,70,30);
        container.add(password);
        password.setVisible(true);

        //password text
        passwordField = new JPasswordField(20);
        passwordField.setBounds(175,130,150,30);
        passwordField.setEchoChar('*');
        passwordField.setVisible(true);
        container.add(passwordField);

        if(resourceBundle != null){
            button.setText(resourceBundle.getString("Done"));
            username.setText(resourceBundle.getString("Name")+":");
            password.setText(resourceBundle.getString("Password")+":");
        }
        setVisible(true);
        setResizable(false);
    }

    private byte[] serialization(Object o) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(o);
        byte [] data = byteArrayOutputStream.toByteArray();
        outputStream.close();
        return data;
    }

    private void clear(){
        user.setText("");
        passwordField.setText("");
    }

    private void register(){
        try{
            CollectionsofPerson collection = basic.getCollection();
            DatagramChannel channel = basic.getChannel();
            basic.setClient(new ClientInformation(user.getText(), String.valueOf(passwordField.getPassword()),true));
            MainRequest request = new MainRequest(basic.getClient());
            byte[] send = serialization(request);
            channel.send(ByteBuffer.wrap(send, 0, send.length), basic.getAddress());
            set:
            while (basic.getSelector().select() > 0) {
                Iterator<SelectionKey> iterator = basic.getSelector().selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isReadable()) {
                        DatagramChannel datagramChannel = (DatagramChannel) selectionKey.channel();
                        ByteBuffer buffer1 = ByteBuffer.allocate(102400);
                        buffer1.clear();
                        datagramChannel.receive(buffer1);
                        buffer1.flip();
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer1.array(), 0, buffer1.array().length);
                        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                        Response firstResopne = (Response) objectInputStream.readObject();
                        collection.setPeople(firstResopne.getPeople());
                        objectInputStream.close();
                        collection.setPeople(firstResopne.getPeople());
                        if (firstResopne.isException()) {
                            selectionKey.interestOps(SelectionKey.OP_READ);
                            clear();
                            new Mistake(firstResopne.getManagerout());
                            break set;
                        } else {
                            selectionKey.interestOps(SelectionKey.OP_WRITE);
                            basic.setUsername(user.getText());
                            dispose();
                            login.dispose();
                            new Worker(basic,resourceBundle);
                            break set;
                        }
                    } else if (!selectionKey.isConnectable()) {
                        if (print) {
                            System.out.print("connecting server...\n");
                            print = false;
                        }
                    }
                }
            }
        }catch (Exception E){
            new Mistake(E.getMessage());
        }
    }
}
