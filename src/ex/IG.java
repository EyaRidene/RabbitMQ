package ex;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class IG extends JPanel{
    private JLabel l1;
    private JTextArea ta1;
    private  JLabel l2;
    private JTextArea ta2;

    public IG(){
        ta1 = new JTextArea(2,2);
        ta1.setBackground(Color.ORANGE);
        ta2 = new JTextArea(2, 2);
        ta2.setBackground(Color.ORANGE);
        ta1.getDocument().addDocumentListener(new DocumentListener() {
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
                    Task1.sendMessage(ta1.getText());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        ta2.setEnabled(false);
        ta1.setLineWrap(true);
        ta1.setWrapStyleWord(true);
        ta2.setText("Blocked Zone");
        ta2.setDisabledTextColor(Color.darkGray);
        l1 = new JLabel("Zone de text 1");
        l2 = new JLabel("Zone de text 2");
        l1.setForeground(new Color(70, 130, 180));
        l2.setForeground(new Color(70, 130, 180));
        JScrollPane scroll1 = new JScrollPane(ta1);
        add(scroll1, BorderLayout.NORTH);
        JScrollPane scroll2 = new JScrollPane(ta2);
        add(scroll2, BorderLayout.NORTH);
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
        JFrame frame = new JFrame ("Text Editor :) ");
        frame.setResizable(false);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new IG());
        frame.pack();
        frame.setVisible (true);
    }
}
