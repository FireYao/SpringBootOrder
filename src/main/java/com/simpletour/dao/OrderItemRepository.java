package com.simpletour.dao;

import com.simpletour.domain.Item;
import com.simpletour.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>, JpaSpecificationExecutor<OrderItem> {

    void deleteAllByOrderId(int orderId);

    List<OrderItem> findByOrderId(int orderId);

}
