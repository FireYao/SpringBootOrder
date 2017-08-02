package com.simpletour.rabbit.manyQueue;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSenderTwo {

    Logger logger = Logger.getLogger(MessageSenderTwo.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String message) {
        rabbitTemplate.convertAndSend("many", message);
        logger.info("sender(2):" + message);
    }


}
