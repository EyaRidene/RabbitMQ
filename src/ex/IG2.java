package ex;

import java.awt.*;
import javax.swing.*;

public class IG2 extends JPanel {
    private JTextArea text1;
    private JTextArea text2;
    private JLabel label1;
    private JLabel label2;

    public IG2() {

        text1 = new JTextArea (2, 2);
        text2 = new JTextArea (2, 2);
        text1.setDisabledTextColor(Color.BLACK);
        text1.setBackground(Color.ORANGE);
        text2.setDisabledTextColor(Color.BLACK);
        text2.setBackground(Color.ORANGE);
        text1.setEnabled(false);
        text2.setEnabled(false);
        text1.setLineWrap(true);
        text1.setWrapStyleWord(true);
        text2.setLineWrap(true);
        text2.setWrapStyleWord(true);

        label1 = new JLabel ("Zone de text 1");
        label2 = new JLabel ("Zone de text 2");
        setPreferredSize (new Dimension (900, 650));
        setLayout (null);
        add (text1);
        add (text2);
        add (label1);
        add (label2);

        text1.setBounds (150, 50, 450, 150);
        text2.setBounds (150, 250, 450, 150);
        label2.setBounds (100, 215, 100, 25);
        label1.setBounds (100, 15, 100, 25);

    }
    void setTextArea1Text(String newText){
        text1.setText(newText);
    }
    void setTextArea2Text(String newText){
        text2.setText(newText);
    }


    public static void main (String[] args) throws Exception {
        IG2 i=new IG2();
        Task3.Task1Cnx(i);
        Task3.Task2Cnx(i);
        JFrame frame = new JFrame ("Text Editor :)");
        frame.setResizable(false);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (i);
        frame.pack();
        frame.setVisible (true);

    }

}
