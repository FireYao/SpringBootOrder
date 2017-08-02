package com.simpletour.rabbit.topicExchange;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicAnySender {

    private static Logger logger = Logger.getLogger(TopicAnySender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String message) {
        rabbitTemplate.convertAndSend("topicExchange", "topic.any", message);
        logger.info("--------------send topicExchange--->>>" + message + "-----------------");
    }
}
