package com.simpletour.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FanoutExchange 模式下会将消息分发给所有绑定到该Exchange的队列
 * 该模式需要提前将Exchange与Queue绑定,一个Exchange可以绑定多个Queue,一个Queue可以同多个Exchange绑定
 * 不需要RouteKey
 */
@Configuration
public class FanoutExchangeConfig {
    /*----------注册3个队列-----------------------*/
    @Bean
    public Queue fanoutAQueue() {
        return new Queue("fanout.a");
    }

    @Bean
    public Queue fanoutBQueue() {
        return new Queue("fanout.b");
    }

    @Bean
    public Queue fanoutCQueue() {
        return new Queue("fanout.c");
    }

    /*------------注册一个FanoutExchange-----------------------*/
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }


    /*--------------分别绑定3个队列---------------------*/
    @Bean
    public Binding bindingExchangeFanoutA(Queue fanoutAQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutAQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeFanoutB(Queue fanoutBQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutBQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeFanoutC(Queue fanoutCQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutCQueue).to(fanoutExchange);
    }
 /*-----------------------------------*/

}
