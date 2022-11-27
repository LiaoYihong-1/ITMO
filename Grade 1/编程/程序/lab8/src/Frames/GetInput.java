package Frames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetInput implements ActionListener {
    private String input;
    private JTextField textField;

    @Override
    public void actionPerformed(ActionEvent e) {
        textField = (JTextField) e.getSource();
        input = textField.getText();
        System.out.print(input+"\n");
    }

    public String getInput() {
        return input;
    }
}
