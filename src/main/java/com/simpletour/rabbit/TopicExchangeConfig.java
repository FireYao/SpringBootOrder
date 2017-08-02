package com.simpletour.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TopicExchange 模式下的消息会发送到所有与指定RouteKey匹配/模糊匹配的Queue上
 * 这种模式需要RouteKey,也需要提前绑定Exchange和Queue
 * 绑定时,提供一个该队列关心的主题，如“#.topic.#”表示该队列关心所有涉及topic的消息(一个RouteKey为”mq.topic.ok”的消息会被转发到该队列)。
 * # 表示0个或若干个关键字，* 表示一个关键字。如 topic.* 能与 topic.a 匹配，无法与 topic.a.queue  匹配；但是 topic.# 能与上述两者匹配。
 */
@Configuration
public class TopicExchangeConfig {

    @Bean
    public Queue topicAQueue() {
        return new Queue("topic.a");
    }

    @Bean
    public Queue topicAnyQueue() {
        return new Queue("topic.any");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding bindingExchangeTopicA(Queue topicAQueue, TopicExchange topicExchange) {
        //绑定指定的队列 topic.a
        return BindingBuilder.bind(topicAQueue).to(topicExchange).with("topic.a");
    }

    @Bean
    public Binding bindingExchangeAnyTopic(Queue topicAnyQueue, TopicExchange topicExchange) {
        //绑定匹配自定义格式的队列topic.#
        //#表示匹配多个关键字 *表示匹配一个关键字
        return BindingBuilder.bind(topicAnyQueue).to(topicExchange).with("topic.#");
    }


}
