package com.simpletour.rabbit.manyQueue;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "many")
public class MessageReceiverTwo {

    Logger logger = Logger.getLogger(MessageReceiverTwo.class);

    @RabbitHandler
    public void process(String message) {
        logger.info("rec(2):" + message);
    }

}
