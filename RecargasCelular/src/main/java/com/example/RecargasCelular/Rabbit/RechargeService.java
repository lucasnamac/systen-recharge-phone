package com.example.RecargasCelular.Rabbit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeoutException;

@Service
public class RechargeService {
    private final BlockingQueue<String> queue = new LinkedBlockingDeque<>();

    @Autowired
    public RechargeService(@Qualifier("consumerConnectionFactory") ConnectionFactory connectionFactory) {
        try (Connection connection = connectionFactory.createConnection();
             Channel channel = connection.createChannel(false)) {

            String queueName = "RechargePlataformToPhoneRecharge";
            channel.queueDeclare(queueName, false, false, false, null);

            channel.basicConsume(queueName, true, (consumerTag, message) -> {
                String content = new String(message.getBody());
                queue.offer(content);
            }, consumerTag -> {
            });

        } catch (IOException | TimeoutException e) {
        }
    }

    public String consumer() throws InterruptedException {
        return queue.take();
    }
}
