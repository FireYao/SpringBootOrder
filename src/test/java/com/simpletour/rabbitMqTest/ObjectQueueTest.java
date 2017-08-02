package com.simpletour.rabbitMqTest;

import com.simpletour.domain.Item;
import com.simpletour.rabbit.objectQueue.ObjectSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectQueueTest {

    @Autowired
    private ObjectSender objectSender;


    @Test
    public void test() {

        Item item = new Item();
        item.setItemId(1);
        item.setItemName("小单车");
        item.setItemPrice(1555);
        item.setItemStock(44);

        objectSender.send(item);
    }

}
