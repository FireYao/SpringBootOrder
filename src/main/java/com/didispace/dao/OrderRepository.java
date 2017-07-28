package com.didispace.dao;

import com.didispace.domain.Order;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Modifying
    @Query("update Order o set o.stauts = :stauts,o.updateTime = :updateTime where o.orderId = :orderId")
    void updateStauts(@Param("stauts") int stauts, @Param("orderId") int orderId, @Param("updateTime") Date updateTime);

    List<Order> findByUserId(int userId);
}
