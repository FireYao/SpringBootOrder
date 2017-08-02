package com.simpletour.rabbit.directExchange;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 火尧 on 2017/7/25.
 */
@Configuration
public class DirectReceiver {

    private static Logger logger = Logger.getLogger(DirectReceiver.class);


    @RabbitListener(queues = "hello")
    public void process(String message) {
        logger.info("direct receiver message::" + message);
    }

}
