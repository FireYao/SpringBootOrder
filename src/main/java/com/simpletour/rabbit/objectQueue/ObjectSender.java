package com.simpletour.rabbit.objectQueue;

import com.alibaba.fastjson.JSONObject;
import com.simpletour.domain.Item;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectSender {

    Logger logger = Logger.getLogger(ObjectSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Item item) {
        rabbitTemplate.convertAndSend("object", item);
        logger.info(JSONObject.toJSONString(item, true));
    }

}
