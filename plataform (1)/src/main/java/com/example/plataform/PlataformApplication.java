package com.example.plataform;

import com.example.plataform.Service.ConsumerRabbitMQ;
import com.example.plataform.Service.Receiver;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class PlataformApplication {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext ctxConsumer = new AnnotationConfigApplicationContext(ConsumerRabbitMQ.class);
        Receiver receiver = (Receiver) ctxConsumer.getBean("receiver");
        receiver.getCountDownLatch().await(200000, TimeUnit.SECONDS);
    }

}
