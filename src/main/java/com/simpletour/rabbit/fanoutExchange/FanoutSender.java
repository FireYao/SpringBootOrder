package com.simpletour.rabbit.fanoutExchange;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

    private static Logger logger = Logger.getLogger(FanoutSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;


    public void send(String message) {
        //发送消息到FanoutExchange
        rabbitTemplate.convertAndSend("fanoutExchange", "", message);
        logger.info("--------------send fanoutExchange--->>>" + message + "-----------------");
    }


}
