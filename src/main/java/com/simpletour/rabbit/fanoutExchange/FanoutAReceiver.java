package com.simpletour.rabbit.fanoutExchange;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutAReceiver {

    private static Logger logger = Logger.getLogger(FanoutAReceiver.class);

    @RabbitListener(queues = "fanout.a")
    public void process(String message) {
        logger.info("fanout.a receiver message::" + message);
    }

}
