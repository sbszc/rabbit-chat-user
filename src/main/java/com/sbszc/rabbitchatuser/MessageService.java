package com.sbszc.rabbitchatuser;

import com.sbszc.rabbitchatuser.amqpconfig.AMQPConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class MessageService {

    private final RabbitTemplate rabbitTemplate;
    private final AMQPConfig amqpConfig;

    public MessageService(RabbitTemplate rabbitTemplate, AMQPConfig amqpConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.amqpConfig = amqpConfig;
    }

    public MessageDto postMessage(MessageDto message) {
        message.getMessageDetails().put("id", UUID.randomUUID().toString());
        message.getMessageDetails().put("date", new Date());
        message.getMessageDetails().put("sourceUser", amqpConfig.user);

        switch (message.getDestinationType()) {
            case "DIRECT" -> postDirectMessage(message);
            case "GROUP" -> postGroupMessage(message);
            default -> throw new RuntimeException("Unsupported destination type");
        }

        return message;
    }

    private void postDirectMessage(MessageDto message) {
        rabbitTemplate.convertAndSend(
                amqpConfig.directChatExchangeName,
                message.getDestination(),
                message);
    }

    private void postGroupMessage(MessageDto message) {
        rabbitTemplate.convertAndSend(
                amqpConfig.groupChatExchangeName,
                message.getDestination(),
                message);
    }
}
