package com.simpletour.rabbitMqTest;

import com.simpletour.rabbit.manyQueue.MessageSenderOne;
import com.simpletour.rabbit.manyQueue.MessageSenderTwo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyQueueTest {


    @Autowired
    private MessageSenderOne messageSenderOne;
    @Autowired
    private MessageSenderTwo messageSenderTwo;


    @Test
    public void oneToMany() {
        for (int i = 0; i < 10; i++) {
            messageSenderOne.send(String.valueOf(i + 1));
        }
    }


    @Test
    public void manyToMany() {
        for (int i = 0; i < 10; i++) {
            messageSenderOne.send(String.valueOf(i + 1));
            messageSenderTwo.send(String.valueOf(i + 1));
        }
    }
}
