package com.simpletour.rabbit.fanoutExchange;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutBReceiver {

    private static Logger logger = Logger.getLogger(FanoutBReceiver.class);

    @RabbitListener(queues = "fanout.b")
    public void process(String message) {
        logger.info("fanout.b receiver message::" + message);
    }

}
