package com.simpletour.rabbit.fanoutExchange;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutCReceiver {

    private static Logger logger = Logger.getLogger(FanoutCReceiver.class);

    @RabbitListener(queues = "fanout.c")
    public void process(String message) {
        logger.info("fanout.c receiver message::" + message);
    }

}
