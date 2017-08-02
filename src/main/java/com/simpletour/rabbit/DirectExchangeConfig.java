package com.simpletour.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 火尧 on 2017/7/25.
 *
 * 直接发送消息到指定的队列,默认情况下使用的是RabbitMQ的 default Exchange
 * 不需要绑定Exchange 只需指定RouteKey
 */
@Configuration
public class DirectExchangeConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }


    @Bean
    public Queue manyQueue() {
        return new Queue("many");
    }

    @Bean
    public Queue objectQueue() {
        return new Queue("object");
    }


}
