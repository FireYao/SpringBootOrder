package com.didispace.dao;

import com.didispace.domain.Order;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer>, PagingAndSortingRepository<Order, Integer> {
    @Modifying
    @Query("update Order o set o.stauts = :stauts,o.updateTime = :updateTime,o.dealTime = :dealTime where o.orderId = :orderId")
    void updateStauts(@Param("stauts") int stauts, @Param("orderId") int orderId, @Param("updateTime") Date updateTime, @Param("dealTime") Date dealTime);

    List<Order> findByUserId(int userId);

}
