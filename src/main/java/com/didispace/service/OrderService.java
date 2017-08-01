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

    void createOrder(List<OrderItem> items) throws Exception;

    void updateStauts(int orderId, int stauts) throws Exception;

    void deleteOrder(int orderId) throws Exception;

    List<Order> findAll() throws Exception;

    Page<Order> findAll(Pageable pageable) throws Exception;

    List<Order> findByUser(int userId) throws Exception;

    Order findById(int orderId) throws Exception;

    List<Order> findAllOnlyOrder() throws Exception;

    void sendOrCloseOrder(int orderId, int stauts) throws Exception;

    Page<Order> findAllOnlyOrder(Pageable pageable) throws Exception;
}
