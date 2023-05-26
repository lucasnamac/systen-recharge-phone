package com.example.plataform.Service;

import com.example.plataform.model.Payments;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import static com.example.plataform.Service.ProducerRabbitMQ.queueName;

public class Receiver {
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private Payments payments;

    public void receiver(byte[] message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            this.payments = mapper.readValue(message, Payments.class);
            System.out.println("Dados do usuário para realizar a recarga");
            System.out.println("Registro de método de pagamento: " + payments.getId());
            System.out.println("Nome no cartão de crédito: " + payments.getCreditCardName());
            System.out.println("Número do cartão de crédito: " + payments.getCreditCardNumber());
            System.out.println("Registro do cliente: " + payments.getIdClient());
            System.out.println("Senha do cartão de crédito: " + payments.getPassword());
            System.out.println("Data de validade do cartão de crédito" + new Date(String.valueOf(payments.getValidThru())));
        } catch (Exception e) {
            System.out.println("Fim da fila");
        }
        if (true) {
            AnnotationConfigApplicationContext ctxProducer = new AnnotationConfigApplicationContext(ProducerRabbitMQ.class);
            RabbitTemplate rabbitTemplate = (RabbitTemplate) ctxProducer.getBean("rabbitTemplate");
            rabbitTemplate.convertAndSend(queueName, "O pagamento foi autorizado para o usuário com cartão de credito registrado em nome de: " + getPayment().getCreditCardName());
            System.out.println("A mensagem foi enviada com sucesso");
        }
        countDownLatch.countDown();
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public Payments getPayment() {
        return payments;

    }
}
