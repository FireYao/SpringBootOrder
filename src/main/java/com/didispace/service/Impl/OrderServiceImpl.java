package com.didispace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.didispace.dao.OrderItemRepository;
import com.didispace.dao.OrderRepository;
import com.didispace.domain.Item;
import com.didispace.domain.Order;
import com.didispace.domain.OrderItem;
import com.didispace.service.OrderItemService;
import com.didispace.service.OrderService;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderRepository orderRepository;
    @Resource
    private OrderItemService orderItemService;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createOrder(List<Item> items) {

        Order order = new Order();
        int amout = items.stream().map(item -> item.getItemPrice() * 3).mapToInt(Integer::intValue).sum();
        order.setAmout(amout);
        order.setCreateTime(new Date());
        order.setStauts(1);
        order.setUserId(1);

        orderRepository.save(order);

        List<OrderItem> oiList = new ArrayList<>();


        items.stream().forEach(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getOrderId());
            orderItem.setItemId(item.getItemId());
            orderItem.setItemName(item.getItemName());
            orderItem.setItemNums(4);
            orderItem.setItemPrice(item.getItemPrice());
            oiList.add(orderItem);
        });
        orderItemService.save(oiList);
    }

    @Override
    public void updateStauts(int orderId, int stauts) {
        orderRepository.updateStauts(stauts, orderId, new Date());
    }

    @Override
    public void deleteOrder(int orderId) {
        orderItemService.deleteByOrderId(orderId);
        orderRepository.delete(orderId);
    }

    @Override
    public List<JSONObject> findAll() {
        List<Order> orders = orderRepository.findAll();
        return getResult(orders);
    }

    @Override
    public List<JSONObject> findByUser(int userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return getResult(orders);
    }


    private List<JSONObject> getResult(List<Order> orders) {
        List<JSONObject> list = new ArrayList<>();
        for (Order order : orders) {
            JSONObject result = new JSONObject();
            result.put("order", order);
            result.put("orderItem", orderItemService.findByOrderId(order.getOrderId()));
            list.add(result);
        }
        return list;
    }

}
