package com.example.plataform.Service;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

import static com.example.plataform.Service.ProducerRabbitMQ.queueName;

public class Main {

    public static void main(String args[]) throws InterruptedException {
        AnnotationConfigApplicationContext ctxProducer = new AnnotationConfigApplicationContext(ProducerRabbitMQ.class);
        RabbitTemplate rabbitTemplate = (RabbitTemplate) ctxProducer.getBean("rabbitTemplate");
        rabbitTemplate.convertAndSend(queueName, "teste");
        AnnotationConfigApplicationContext ctxConsumer = new AnnotationConfigApplicationContext(ConsumerRabbitMQ.class);
        Receiver receiver = (Receiver) ctxConsumer.getBean("receiver");
        receiver.getCountDownLatch().await(200000, TimeUnit.SECONDS);

    }
}
