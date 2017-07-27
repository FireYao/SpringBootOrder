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
import javax.validation.constraints.Null;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
    public void createOrder(List<OrderItem> items) {
        Order order = new Order();

        int amout = items.stream()
                .map(orderItem -> orderItem.getItemNums() * orderItem.getItemPrice())
                .mapToInt(Integer::intValue)
                .sum();

        order.setAmout(amout);
        order.setCreateTime(new Date());
        order.setStauts(1);
        order.setUserId(1);

        orderRepository.save(order);

        items.stream().forEach(item -> {
            item.setOrderId(order.getOrderId());
        });
        orderItemService.save(items);
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
    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAll();
        return getResult(orders);
    }

    @Override
    public List<Order> findByUser(int userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return getResult(orders);
    }

    @Override
    public Order findById(int orderId) {
        Order order = orderRepository.findOne(orderId);
        order.setOrderItems(orderItemService.findByOrderId(orderId));
        return order;
    }


    private List<Order> getResult(List<Order> orders) {

        orders.stream().forEach(order -> {
            order.setOrderItems(orderItemService.findByOrderId(order.getOrderId()));
        });

        return orders;
    }

}
