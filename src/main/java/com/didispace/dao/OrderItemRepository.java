package com.didispace.dao;

import com.didispace.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    void deleteAllByOrderId(int orderId);

    List<OrderItem> findByOrderId(int orderId);

}
