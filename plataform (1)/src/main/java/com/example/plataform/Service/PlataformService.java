package com.example.plataform.Service;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Service
public class PlataformService {
    private final BlockingQueue<String> queue = new LinkedBlockingDeque<>();

    @Autowired
    public void PlataformServiceService(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames("RechargePlataformToPhoneRecharge");
        container.setMessageListener(message -> {
            String content = new String(message.getBody());
            queue.offer(content);
        });
        container.start();
    }

    public String consumer() throws InterruptedException {
        return queue.take();
    }
}
