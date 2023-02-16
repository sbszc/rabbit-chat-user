package com.sbszc.rabbitchatuser;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = "${amqp-config.user}")
    public void listener(MessageDto message) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(message.getMessageDetails().get("sourceUser"));
        if (message.getDestinationType().equals("GROUP"))
            stringBuilder.append("(").append(message.getDestination()).append(")");
        stringBuilder.append(": ").append(message.getMessage());

        System.out.println(stringBuilder);
    }
}
