package com.sbszc.rabbitchatuser.amqpconfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AMQPConfig {

    @Value("${amqp-config.direct-chat-exchange-name}")
    public String directChatExchangeName;
    @Value("${amqp-config.group-chat-exchange-name}")
    public String groupChatExchangeName;
    @Value("${amqp-config.user}")
    public String user;
    @Value("${amqp-config.groups}")
    public List<String> groups;

    @Bean
    public DirectChatExchange directChatExchange() {
        return new DirectChatExchange(directChatExchangeName);
    }

    @Bean
    public GroupChatExchange groupChatExchange() {
        return new GroupChatExchange(groupChatExchangeName);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(user);
    }

    @Bean
    public Declarables bindings(Queue userQueue, DirectChatExchange directChatExchange, GroupChatExchange groupChatExchange) {
        var bindings = new ArrayList<Binding>();

        bindings.add(
                BindingBuilder
                        .bind(userQueue)
                        .to(directChatExchange)
                        .with(user)
        );

        groups.forEach(group -> {
            bindings.add(
                    BindingBuilder
                            .bind(userQueue)
                            .to(groupChatExchange)
                            .with(group)
            );
        });

        return new Declarables(bindings);
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
