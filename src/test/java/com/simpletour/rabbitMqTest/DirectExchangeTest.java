package com.simpletour.rabbitMqTest;

import com.simpletour.rabbit.directExchange.DirectSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DirectExchangeTest {

    @Autowired
    private DirectSender directSender;

    @Test
    public void test() throws Exception {
        directSender.send("✔✔✔✔✔✔✔✔✔");
    }
}
