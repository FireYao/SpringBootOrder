package com.simpletour.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 火尧 on 2017/7/25.
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msg) {
//        String context = "hello " + new Date();
//        System.out.println("Sender : " + msg);
        this.rabbitTemplate.convertAndSend("simpletour", msg);
    }

}
