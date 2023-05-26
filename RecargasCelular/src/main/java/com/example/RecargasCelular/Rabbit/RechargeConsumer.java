package com.example.RecargasCelular.Rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

public class RechargeConsumer {

    private final RabbitTemplate rabbitTemplate;

    public RechargeConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String consumer() throws InterruptedException {
        AnnotationConfigApplicationContext ctxConsumer = new AnnotationConfigApplicationContext(ConsumerRabbitMQ.class);
        Receiver receiver = (Receiver) ctxConsumer.getBean("receiver");
        receiver.getCountDownLatch().await(200000, TimeUnit.SECONDS);
        return receiver.getMessage();
    }
}
