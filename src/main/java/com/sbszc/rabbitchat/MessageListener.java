package com.sbszc.rabbitchat;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = "${amqp-config.user}")
    public void listener(MessageDto message) {
        System.out.println(message.getMessage());
    }
}
