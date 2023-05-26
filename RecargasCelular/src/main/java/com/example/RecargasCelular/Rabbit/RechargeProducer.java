package com.example.RecargasCelular.Rabbit;

import com.example.RecargasCelular.model.Payments;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RechargeProducer {

    private String queueName = "PhoneRechargeToRechargePlataform";

    private final RabbitTemplate rabbitTemplate;

    public RechargeProducer(@Qualifier("producerRabbit") RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Payments payments) {
        rabbitTemplate.convertAndSend(queueName, payments);
    }
}