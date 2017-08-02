package com.simpletour.rabbit.objectQueue;

import com.alibaba.fastjson.JSONObject;
import com.simpletour.domain.Item;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ObjectReciver {

    Logger logger = Logger.getLogger(ObjectReciver.class);

    @RabbitListener(queues = "object")
    public void process(Item item) {
        logger.info(JSONObject.toJSONString(item, true));
    }

}
