package Frames;
import Collection.*;
import Command.AbstractCommand;
import Command.Add;
import Lab.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class CreatePersonFrame extends JFrame {
    Integer x = null;
    Integer y = null;
    Integer z = null;
    String locationName = null;
    EyeColor eyeColor = null;
    HairColor hairColor = null;
    int height = 0;
    String name = null;
    private Person p = null;
    private Basic basic;

    JTextField jx;
    JTextField jy;
    JTextField jz;
    JTextField jlocationName;
    JTextField jeyeColor;
    JTextField jhairColor;
    JTextField jheight;
    JTextField jname;

    public CreatePersonFrame(String Topic, Basic basic, AbstractCommand command){
        super(Topic);
        this.basic = basic;
        setBounds(800,300,550,400);
        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(2);
        gridLayout.setRows(4);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Container container = getContentPane();

        jx = setField(jx);
        jy = setField(jy);
        jz = setField(jz);
        jlocationName = setField(jlocationName);
        jeyeColor = setField(jeyeColor);
        jhairColor = setField(jhairColor);
        jheight = setField(jheight);
        jname = setField(jname);

        JPanel px = setPanel(new JPanel());
        JPanel py = setPanel(new JPanel());
        JPanel pz = setPanel(new JPanel());
        JPanel plocalName = setPanel(new JPanel());
        JPanel peyeColor = setPanel(new JPanel());
        JPanel phairColor = setPanel(new JPanel());
        JPanel pheight = setPanel(new JPanel());
        JPanel pname = setPanel(new JPanel());

        JLabel xl = setLabel("Input x(<=79):");
        JLabel yl = setLabel("Input y(y<=794):");
        JLabel zl = setLabel("Input z(>0):");
        JLabel locationNamel = setLabel("Name of location:");

        JLabel hairColorl = setLabel("Set hairColor from: Red/Black/Orange/White");


        JLabel eyeColorl = setLabel("Set eyecolor from: Black/Yellow/Brown");

        JLabel heightl = setLabel("Set height(>0)");
        JLabel namel = setLabel("Name of Person:");

        px.add(xl);
        px.add(jx);

        py.add(yl);
        py.add(jy);

        pz.add(zl);
        pz.add(jz);

        plocalName.add(locationNamel);
        plocalName.add(jlocationName);

        peyeColor.add(eyeColorl);
        peyeColor.add(jeyeColor);

        phairColor.add(hairColorl);
        phairColor.add(jhairColor);

        pname.add(namel);
        pname.add(jname);

        pheight.add(heightl);
        pheight.add(jheight);

        JPanel sum = new JPanel();
        sum.setVisible(true);
        sum.setLayout(gridLayout);
        sum.add(pname);
        sum.add(px);
        sum.add(py);
        sum.add(pz);
        sum.add(plocalName);
        sum.add(pheight);
        sum.add(peyeColor);
        sum.add(phairColor);
        container.add(sum,BorderLayout.CENTER);

        JButton button = new JButton("Done");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!allForm()){
                    new Mistake("Please make sure that all fields are filed!");
                }else {
                    try{
                        buildHair(jhairColor.getText().toUpperCase());
                        buildEye(jeyeColor.getText().toUpperCase());
                        buildHeight(jheight.getText());
                        buildx(jx.getText());
                        buildz(jz.getText());
                        buildy(jy.getText());
                        name = jname.getText();
                        locationName = jlocationName.getText();
                        Location location = new Location(new Coordinates(x,y),locationName,z);
                        p = new Person(location,hairColor,eyeColor,name,height);
                        p.setHost(basic.getUsername());
                        CommandPackage commandPackage = new CommandPackage(command,p);
                        MainRequest mainRequest = new MainRequest(basic.getClient(),commandPackage);
                        byte [] data = serialization(mainRequest);
                        Selector selector = basic.getSelector();
                        all: while(selector.select() > 0){
                            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                            while (iterator.hasNext()){
                                SelectionKey selectionKey = iterator.next();
                                iterator.remove();
                                if(selectionKey.isWritable()){
                                    DatagramChannel channel = (DatagramChannel) selectionKey.channel();
                                    channel.send(ByteBuffer.wrap(data),basic.getAddress());
                                    selectionKey.interestOps(SelectionKey.OP_READ);
                                }else if(selectionKey.isReadable()){
                                    DatagramChannel channel = (DatagramChannel) selectionKey.channel();
                                    ByteBuffer buffer1 = ByteBuffer.allocate(102400);
                                    buffer1.clear();
                                    channel.receive(buffer1);
                                    buffer1.flip();
                                    ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(buffer1.array()));
                                    Response response = (Response) objectInputStream.readObject();
                                    //String E = new String(buffer1.array(), 0, 4, StandardCharsets.UTF_8);
                                    String print = response.getManagerout();
                                    Person.setIdcode(response.getId());
                                    Worker.collection.setPeople(response.getPeople());
                                    Worker.area.append(print);
                                    selectionKey.interestOps(SelectionKey.OP_WRITE);
                                    break all;
                                }
                            }
                        }
                        dispose();
                    }catch (Exception ex){
                        ex.printStackTrace();
                        //new Mistake(ex.getMessage());
                    }
                }
            }
        });
        container.add(button,BorderLayout.SOUTH);
        setVisible(true);
    }

    private JTextField setField(JTextField f){
        f = new JTextField(15);
        f.setVisible(true);
        return f;
    }

    private JPanel setPanel(JPanel panel){
        panel.setSize(100,60);
        GridLayout g = new GridLayout();
        g.setColumns(1);
        g.setRows(2);
        panel.setLayout(g);
        panel.setVisible(true);
        return panel;
    }

    private JLabel setLabel (String label){
        JLabel j = new JLabel(label);
        j.setVisible(true);
        return j;
    }

    /**
     * check all text field was filled
     * @return true when all field was filled
     * @return false when there is any field empty
     */
    private boolean allForm(){
        if(!jx.getText().equals("") && !jy.getText().equals("") && !jz.getText().equals("") && !jlocationName.getText().equals("") && !jname.getText().equals("") && !jheight.getText().equals("") && !jhairColor.getText().equals("") && !jeyeColor.equals("")){
            return true;
        }else {
            return false;
        }
    }

    private void buildx(String s) throws ValueTooBigException,NumberFormatException {
        this.x = Integer.valueOf(s);
        if(x > 79){
            throw new ValueTooBigException("x should smaller than 79");
        }
    }

    private void buildy(String s) throws ValueTooBigException,NumberFormatException {
        this.y = Integer.valueOf(s);
        if(y > 794)
            throw new ValueTooBigException("y should smaller than 794");
    }

    private void buildz(String s) throws ValueTooSmallException, NumberFormatException{
        this.z = Integer.valueOf(s);
        if(z < 0)
            throw new ValueTooSmallException("z should bigger than 0");
    }

    private void buildHeight(String s) throws ValueTooSmallException,NumberFormatException{
        this.height = Integer.valueOf(s);
        if(height <= 0)
            throw new ValueTooSmallException("Height should bigger than 0");
    }

    private void buildEye (String s) throws  IllegalArgumentException{
        this.eyeColor = EyeColor.valueOf(s);
    }

    private void buildHair (String s) throws IllegalArgumentException{
        this.hairColor = HairColor.valueOf(s);
    }

    @Override
    public String getName() {
        return name;
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
