package com.didispace.service.Impl;

import com.didispace.dao.OrderItemRepository;
import com.didispace.domain.Item;
import com.didispace.domain.OrderItem;
import com.didispace.service.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private OrderItemRepository orderItemRepository;

    @Override
    public void save(List<OrderItem> orderItem) {
        for (int i = 0; i < orderItem.size(); i++) {
            entityManager.persist(orderItem.get(i));
            if (i % 30 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }

    }

    @Override
    public void deleteByOrderId(int orderId) {
        orderItemRepository.deleteAllByOrderId(orderId);
    }

    @Override
    public List<OrderItem> findByOrderId(int orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
}
