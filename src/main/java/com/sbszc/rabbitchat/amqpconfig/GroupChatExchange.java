package com.sbszc.rabbitchat.amqpconfig;

import org.springframework.amqp.core.TopicExchange;

public class GroupChatExchange extends TopicExchange {
    public GroupChatExchange(String name) {
        super(name);
    }
}
