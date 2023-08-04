package Frames;

import javax.swing.*;
import java.awt.*;

public class Mistake extends JFrame {
    public Mistake(String information){
        JLabel label = new JLabel(information);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setBounds(800,350,300,100);
        setResizable(false);
        setVisible(true);
        Container container = getContentPane();
        container.add(label,BorderLayout.CENTER);
    }
}
