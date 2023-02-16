package com.sbszc.rabbitchatuser.amqpconfig;

import org.springframework.amqp.core.DirectExchange;

public class DirectChatExchange extends DirectExchange {
    public DirectChatExchange(String name) {
        super(name);
    }
}
