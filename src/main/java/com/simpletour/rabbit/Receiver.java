package com.simpletour.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 火尧 on 2017/7/25.
 */
@Configuration
@RabbitListener(queues = "simpletour")
public class Receiver {

    @RabbitHandler
    public void process(String fireyao) {
        System.out.println("Receiver : " + fireyao);
    }


}
