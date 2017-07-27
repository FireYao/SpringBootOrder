package com.didispace.service;

import com.alibaba.fastjson.JSONObject;
import com.didispace.domain.Item;
import com.didispace.domain.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    void createOrder(List<Item> items);

    void updateStauts(int orderId,int Stauts);

    void deleteOrder(int orderId);

    List<JSONObject> findAll();

    List<JSONObject> findByUser(int userId);


}
