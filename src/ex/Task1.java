package ex;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Task1 {
    public static void sendMessage(String message) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()){
            channel.queueDeclare("task1",false,false,false,null);
            channel.basicPublish("", "task1", null, message.getBytes());
        }
    }
}
