package com.simpletour.rabbitMqTest;

import com.simpletour.rabbit.topicExchange.TopicASender;
import com.simpletour.rabbit.topicExchange.TopicAnySender;
import com.simpletour.rabbit.topicExchange.TopicXSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicExchangeTest {

    @Autowired
    private TopicASender topicASender;

    @Autowired
    private TopicAnySender topicAnySender;

    @Autowired
    private TopicXSender topicXSender;


    @Test
    public void topicASend() throws Exception {
        topicASender.send("topicA---> send <---");
    }

    @Test
    public void topicAnySend() throws Exception {
        topicAnySender.send("topicAny ---> send <---");
    }

    @Test
    public void topicXsend() throws Exception {
        topicXSender.send("topicX ---> send <---");
    }
}
