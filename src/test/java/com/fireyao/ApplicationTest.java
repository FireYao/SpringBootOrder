package com.fireyao;

import com.alibaba.fastjson.JSONObject;
import com.didispace.Application;
import com.didispace.dao.ItemRepository;
import com.didispace.dao.OrderItemRepository;
import com.didispace.dao.OrderRepository;
import com.didispace.domain.Item;
import com.didispace.domain.Order;
import com.didispace.rabbit.Sender;
import com.didispace.service.ItemService;
import com.didispace.service.OrderItemService;
import com.didispace.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
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

    @Resource
    private OrderItemRepository orderItemRepository;

    @Resource
    private OrderItemService orderItemService;

    @Resource
    private OrderService orderService;

    @Resource
    private OrderRepository orderRepository;

    @Test
    public void test() {
        sender.send();
    }

    @Test
    public void itemTest() throws Exception {
        Item item = new Item();
        item.setItemName("膜xx单车");
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

    @Test
    public void name3() throws Exception {
//        orderItemService.deleteByOrderId(2);

        List<Item> items = itemService.findItemsByPrice(100);
        orderService.createOrder(items);

//        orderRepository.setCreateTime(new Date(),16);

    }

    @Test
    public void name4() throws Exception {

        List<JSONObject> all = orderService.findAll();
//        List<Order> orders = orderRepository.findByUserId(1);
//        List<Item> all = itemService.findItemsByPrice(100);
        print(all);

    }

    public void print(Object obj) {
        System.out.println(JSONObject.toJSONString(obj, true));
    }

}
