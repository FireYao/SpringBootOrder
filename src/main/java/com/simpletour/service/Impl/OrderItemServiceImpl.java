package com.simpletour.service.Impl;

import com.simpletour.dao.OrderItemRepository;
import com.simpletour.domain.OrderItem;
import com.simpletour.service.OrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderItemServiceImpl implements OrderItemService {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private OrderItemRepository orderItemRepository;

    @Override
    @Transactional
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
    @Transactional
    public void deleteByOrderId(int orderId) throws Exception {
        orderItemRepository.deleteAllByOrderId(orderId);
    }

    @Override
    public List<OrderItem> findByOrderId(int orderId) throws Exception {
        return orderItemRepository.findByOrderId(orderId);
    }
}
