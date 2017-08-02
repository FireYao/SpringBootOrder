package com.simpletour.service.Impl;

import com.simpletour.dao.OrderRepository;
import com.simpletour.domain.Order;
import com.simpletour.domain.OrderItem;
import com.simpletour.service.ItemService;
import com.simpletour.service.OrderItemService;
import com.simpletour.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderRepository orderRepository;
    @Resource
    private OrderItemService orderItemService;
    @Resource
    private ItemService itemService;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createOrder(List<OrderItem> items) throws Exception {

        //修改库存
        items.forEach(item -> {
            itemService.updateItemStock(item.getItemId(), item.getItemNums());
        });

        Order order = new Order();
        int amout = items.stream()
                .map(orderItem -> orderItem.getItemNums() * orderItem.getItemPrice())
                .mapToInt(Integer::intValue)
                .sum();

        order.setAmout(amout);
        order.setCreateTime(new Date());
        order.setStauts(1);
        order.setUserId(1);

        orderRepository.save(order);

        items.stream().forEach(item -> {
            item.setOrderId(order.getOrderId());
        });
        orderItemService.save(items);
    }

    @Override
    public void updateStauts(int orderId, int stauts) {
        Date dealTime = null;
        if (stauts == 3)
            dealTime = new Date();
        if (stauts == 2)
            return;
        orderRepository.updateStauts(stauts + 1, orderId, new Date(), dealTime);
    }

    @Override
    public void deleteOrder(int orderId) throws Exception {
        orderItemService.deleteByOrderId(orderId);
        orderRepository.delete(orderId);
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAll().stream().sorted((o1, o2) -> o1.getOrderId() - o2.getOrderId()).collect(Collectors.toList());
        getResult(orders);
        return orders;
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        Page<Order> page = orderRepository.findAll(pageable);
        getResult(page.getContent());
        return page;
    }

    @Override
    public List<Order> findByUser(int userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        getResult(orders);
        return orders;
    }

    @Override
    public Order findById(int orderId) throws Exception {
        Order order = orderRepository.findOne(orderId);
        order.setOrderItems(orderItemService.findByOrderId(orderId));
        return order;
    }

    @Override
    public List<Order> findAllOnlyOrder() {
        return orderRepository.findAll();
    }

    @Override
    public void sendOrCloseOrder(int orderId, int stauts) {

        orderRepository.updateStauts(stauts + 1, orderId, new Date(), null);
    }

    @Override
    public Page<Order> findAllOnlyOrder(Pageable pageable) {
        Page<Order> page = orderRepository.findAll(pageable);
        return page;
    }


    private void getResult(List<Order> orders) {

        orders.stream().forEach(order -> {
            try {
                order.setOrderItems(orderItemService.findByOrderId(order.getOrderId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

}
