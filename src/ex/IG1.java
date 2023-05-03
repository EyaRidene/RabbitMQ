package ex;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class IG1 extends JPanel{
    private JLabel l1;
    private JTextArea ta1;
    private  JLabel l2;
    private JTextArea ta2;

    public IG1(){
        ta1 = new JTextArea(6,6);
        ta1.setBackground(Color.ORANGE);
        ta2 = new JTextArea(6, 6);
        ta2.setBackground(Color.ORANGE);
        ta2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                task();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                task();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                task();
            }
            public void task(){
                try{
                    Task2.sendMessage(ta2.getText());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        ta1.setEnabled(false);
        ta2.setLineWrap(true);
        ta2.setWrapStyleWord(true);
        ta1.setText("Blocked Zone");
        l1 = new JLabel("Zone de text 1");
        l2 = new JLabel("Zone de text 2");
        l1.setForeground(new Color(70, 130, 180));
        l2.setForeground(new Color(70, 130, 180));
        setPreferredSize(new Dimension(700, 500));
        setLayout (null);
        add (ta1);
        add (ta2);
        add (l1);
        add (l2);
        ta1.setBounds (150, 50, 450, 150);
        ta2.setBounds (150, 250, 450, 150);
        l2.setBounds (100, 215, 100, 25);
        l1.setBounds (100, 15, 100, 25);
    }
    public static void main (String[] args) {
        JFrame frame = new JFrame ("Text Editor");
        frame.setResizable(false);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new IG1());
        frame.pack();
        frame.setVisible (true);
    }
}