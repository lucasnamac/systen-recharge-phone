package com.example.RecargasCelular.Rabbit;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableAutoConfiguration
public class ConsumerRabbitMQ {
    final static String queueName = "RechargePlataformToPhoneRecharge";

    @Primary
    @Bean("consumerConnectionFactory")
    public ConnectionFactory connectionFactory()
    {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("rabbitmq");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean("consumerQueue")
    Queue queue()
    {
        return new Queue(queueName, false);
    }

    @Bean("consumerExchange")
    TopicExchange exchange()
    {
        return new TopicExchange("api_exchange");
    }

    @Bean("consumerBinding")
    Binding binding(@Qualifier("consumerQueue") Queue queue, @Qualifier("consumerExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }


    @Bean("consumerSimpleMessage")
    SimpleMessageListenerContainer container(@Qualifier("consumerConnectionFactory") ConnectionFactory connectionFactory, @Qualifier("listener") MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    Receiver receiver() {
        return new Receiver();
    }

    @Bean("listener")
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiver");
    }

    @Bean("consumerRabbit")
    public RabbitTemplate rabbitTemplate(@Qualifier("consumerConnectionFactory") final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

}
