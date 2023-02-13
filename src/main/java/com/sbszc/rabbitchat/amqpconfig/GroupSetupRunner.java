package com.sbszc.rabbitchat.amqpconfig;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class GroupSetupRunner implements ApplicationRunner {

    private final AMQPConfig amqpConfig;
    private final AmqpAdmin amqpAdmin;
    private final Queue userQueue;
    private final GroupChatExchange groupChatExchange;

    public GroupSetupRunner(AmqpAdmin amqpAdmin, GroupChatExchange groupChatExchange, Queue userQueue, AMQPConfig amqpConfig) {
        this.amqpAdmin = amqpAdmin;
        this.groupChatExchange = groupChatExchange;
        this.userQueue = userQueue;
        this.amqpConfig = amqpConfig;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        amqpConfig.groups.forEach(group -> {
            amqpAdmin.declareBinding(BindingBuilder
                    .bind(userQueue)
                    .to(groupChatExchange)
                    .with(group)
            );
        });
    }
}
