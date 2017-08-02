package com.simpletour.service;

import com.simpletour.domain.Order;
import com.simpletour.domain.OrderItem;
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
