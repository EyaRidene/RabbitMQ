package pack1;

import com.rabbitmq.client.Delivery;

import java.io.IOException;

@FunctionalInterface
public interface DeliverCallBack {
    void handle ( String consumerTag, Delivery message) throws IOException;
}
