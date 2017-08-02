package com.simpletour.rabbit.directExchange;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 火尧 on 2017/7/25.
 */
@Component
public class DirectSender {

    private static Logger logger = Logger.getLogger(DirectSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;


    public void send(String message) {
        this.rabbitTemplate.convertAndSend("hello", message);
        logger.info("--------------send directExchange--->>>" + message + "-----------------");
    }
}
