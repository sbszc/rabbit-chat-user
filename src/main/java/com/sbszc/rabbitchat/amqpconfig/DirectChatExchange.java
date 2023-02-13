package com.sbszc.rabbitchat.amqpconfig;

import org.springframework.amqp.core.DirectExchange;

public class DirectChatExchange extends DirectExchange {
    public DirectChatExchange(String name) {
        super(name);
    }
}
