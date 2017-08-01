package com.didispace.service;

import com.didispace.domain.Item;
import com.didispace.domain.OrderItem;

import java.util.List;

public interface OrderItemService {

    void save(List<OrderItem> orderItem) throws Exception;

    void deleteByOrderId(int orderId) throws Exception;

    List<OrderItem> findByOrderId(int orderId) throws Exception;

}
