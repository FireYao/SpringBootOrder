package com.simpletour.service.Impl;

import com.simpletour.dao.OrderItemRepository;
import com.simpletour.domain.OrderItem;
import com.simpletour.service.OrderItemService;
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
    public void save(List<OrderItem> orderItem) throws Exception {
        for (int i = 0; i < orderItem.size(); i++) {
            entityManager.persist(orderItem.get(i));
            if (i % 10 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }

    }

    @Override
    public void deleteByOrderId(int orderId) throws Exception {
        orderItemRepository.deleteAllByOrderId(orderId);
    }

    @Override
    public List<OrderItem> findByOrderId(int orderId) throws Exception {
        return orderItemRepository.findByOrderId(orderId);
    }
}
