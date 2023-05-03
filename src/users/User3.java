package users;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class User3 {
    private final static String EXCHANGE_NAME = "p1";
    private final static String EXCHANGE_NAME2 = "priority";

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        channel.exchangeDeclare(EXCHANGE_NAME2, "fanout");
        String queue_name = channel.queueDeclare().getQueue();
        channel.queueBind(queue_name, EXCHANGE_NAME, "");

        String queue_name2 = channel.queueDeclare().getQueue();
        channel.queueBind(queue_name2, EXCHANGE_NAME2, "");
//
        JLabel l1, l2;
        String msg = "", msg2 = "";
        JFrame f = new JFrame();
        l2 = new JLabel("");
        l2.setForeground(Color.black);
        f.setTitle("User (3)");
        f.setSize(600, 300);
        f.getContentPane().setBackground(new Color(32, 32, 32));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        l1 = new JLabel("P3: ");
        topPanel.add(l1);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        JTextArea text = new JTextArea(msg);
        text.setForeground(Color.white);
        text.setBackground(new Color(48, 48, 48));
        centerPanel.add(text, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton btn = new JButton("done");
        bottomPanel.add(btn);
        btn.setBackground(new Color(64, 64, 64));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(new Color(96, 96, 96), 2));
        btn.setFocusPainted(false);

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = text.getText();
                try {
                    channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("[user3] sent '" + message + "' ");
                String txt="done";
                try {
                    channel.basicPublish(EXCHANGE_NAME2, "", null, txt.getBytes());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String txt = "user3";
                try {
                    channel.basicPublish(EXCHANGE_NAME2, "", null, txt.getBytes());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        f.add(topPanel, BorderLayout.NORTH);
        bottomPanel.add(l2);
        f.add(centerPanel, BorderLayout.CENTER);
        f.add(bottomPanel, BorderLayout.SOUTH);
        f.setVisible(true);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            text.setText(message);
        };
        channel.basicConsume(queue_name, true, "", deliverCallback, consumerTag -> {
        });

        DeliverCallback deliverCallback2 = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(message);
            if(message.equals("user1") || message.equals("user2") ){
                text.setEditable(false);
                l2.setText( " Paragragh locked because "+message +" is typing !");
            }else if(message.equals("done")){
                text.setEditable(true);
                l2.setText("");
            }

        };
        channel.basicConsume(queue_name2, true, "", deliverCallback2, consumerTag -> {
        });
    }

    ;
}
