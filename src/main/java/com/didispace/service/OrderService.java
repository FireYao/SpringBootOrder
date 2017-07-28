package com.didispace.service;

import com.alibaba.fastjson.JSONObject;
import com.didispace.domain.Item;
import com.didispace.domain.Order;
import com.didispace.domain.OrderItem;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    void createOrder(List<OrderItem> items);

    void updateStauts(int orderId, int stauts);

    void deleteOrder(int orderId);

    List<Order> findAll();

    Page<Order> findAll(Pageable pageable);

    List<Order> findByUser(int userId);

    Order findById(int orderId);

    List<Order> findAllOnlyOrder();

    void sendOrCloseOrder(int orderId,int stauts);

    Page<Order> findAllOnlyOrder(Pageable pageable);
}
