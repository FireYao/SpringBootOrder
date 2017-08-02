package com.simpletour.rabbit.topicExchange;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicAnyReceiver {

    private static Logger logger = Logger.getLogger(TopicAnyReceiver.class);

    @RabbitListener(queues = "topic.any")
    public void process(String message) {
        logger.info("topic.any receiver message::" + message);
    }

}
