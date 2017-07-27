package com.didispace.service;

import com.alibaba.fastjson.JSONObject;
import com.didispace.domain.Item;
import com.didispace.domain.Order;
import com.didispace.domain.OrderItem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    void createOrder(List<OrderItem> items);

    void updateStauts(int orderId,int Stauts);

    void deleteOrder(int orderId);

    List<Order> findAll();

    List<Order> findByUser(int userId);

    Order findById(int orderId);

}
