package com.fireyao;

import com.alibaba.fastjson.JSONObject;
import com.didispace.Application;
import com.didispace.dao.ItemRepository;
import com.didispace.domain.Item;
import com.didispace.rabbit.Sender;
import com.didispace.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @Autowired
    private Sender sender;

    @Resource
    private ItemService itemService;

    @Resource
    private ItemRepository itemRepository;

    @Test
    public void test() {
        sender.send();
    }

    @Test
    public void itemTest() throws Exception {
        Item item = new Item();
        item.setItemId(3);
        item.setItemName("膜拜单车");
        item.setItemPrice(299);
        item.setItemStock(999);
        itemService.save(item);

        /*List<Item> all = itemRepository.findAll();
        List<Item> collect = all.stream().filter(item1 -> item1.getItemStock() >= 1000)
                .collect(Collectors.toList());*/
//        System.out.println(JSONObject.toJSONString(collect, true));

//        List<Item> items = itemRepository.findByItemNameContaining("小");
//        System.out.println(JSONObject.toJSONString(items, true));

    }


    @Test
    public void name1() throws Exception {
        List<Item> itemsByPrice = itemService.findItemsByPrice(100);
        System.out.println(JSONObject.toJSONString(itemsByPrice, true));
    }

    @Test
    public void name2() throws Exception {

        List<Item> byConditions = itemService.findByConditions("车", null, 1000);
        print(byConditions);

    }


    public void print(Object obj) {
        System.out.println(JSONObject.toJSONString(obj, true));
    }

}
