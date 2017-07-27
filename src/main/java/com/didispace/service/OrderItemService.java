package com.didispace.service;

import com.didispace.domain.Item;
import com.didispace.domain.OrderItem;

import java.util.List;

public interface OrderItemService {

    void save(List<OrderItem> orderItem);

    void deleteByOrderId(int orderId);

    List<OrderItem> findByOrderId(int orderId);


}
