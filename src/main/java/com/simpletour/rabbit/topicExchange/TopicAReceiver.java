package com.simpletour.rabbit.topicExchange;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicAReceiver {

    private static Logger logger = Logger.getLogger(TopicAReceiver.class);

    @RabbitListener(queues = "topic.a")
    public void process(String message){
        logger.info("topic.a receiver message::" + message);
    }

}
