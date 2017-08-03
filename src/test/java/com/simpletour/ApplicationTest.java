package com.simpletour;

import com.alibaba.fastjson.JSONObject;
import com.simpletour.dao.ItemRepository;
import com.simpletour.dao.OrderItemRepository;
import com.simpletour.dao.OrderRepository;
import com.simpletour.domain.*;
import com.simpletour.domain.Order;
import com.simpletour.service.ItemService;
import com.simpletour.service.OrderItemService;
import com.simpletour.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @Resource
    private EntityManager entityManager;

    @Resource
    private ItemService itemService;

    @Resource
    private ItemRepository itemRepository;

    @Resource
    private OrderItemRepository orderItemRepository;

    @Resource
    private OrderItemService orderItemService;

    @Resource
    private OrderService orderService;

    @Resource
    private OrderRepository orderRepository;


    @Test
    public void itemTest() throws Exception {
        Item item = new Item();
        item.setItemName("膜xx单车");
        item.setItemPrice(299);
        item.setItemStock(999);
        itemService.save(item);

        /*List<Item> all = itemRepository.findAll();
        List<Item> collect = all.stream().filter(item1 -> item1.getItemStock() >= 1000)
                .collect(Collectors.toList());*/
//        System.out.println(JSONObject.toJSONString(collect, true));

//        List<Item> items = itemRepository.findByItemNameContaining("小");
//        System.out.println(JSONObject.toJSONString(items, true));

    }


    @Test
    public void name1() throws Exception {
        List<Item> itemsByPrice = itemService.findItemsByPrice(100);
        System.out.println(JSONObject.toJSONString(itemsByPrice, true));
    }

    @Test
    public void name2() throws Exception {

        List<Item> byConditions = itemService.findByConditions("车", null, 1000);
        print(byConditions);

    }

    @Test
    public void name3() throws Exception {
        itemService.updateItemStock(14, 10);
    }

    @Test
    public void name4() throws Exception {

        List<Order> orders = orderService.findAll();
//        List<Order> orders = orderRepository.findByUserId(1);
        print(orders);

    }

    @Test
    public void name5() throws Exception {

        orderService.updateStauts(1, 3);

    }

    @Test
    public void name6() throws Exception {

        Pageable pageable = new PageRequest(0, 2);
        Page<Order> page = orderService.findAll(pageable);
        System.out.println(page.getTotalPages());
//        System.out.println(page.getTotalElements());
        List<Order> orders = page.getContent();
        print(orders);

    }

    @Test
    @Transactional
    public void name7() throws Exception {
        Sort sort = new Sort(Sort.Direction.ASC, "orderId");
        Pageable pageable = new PageRequest(0, 4, sort);
        Page<Order> all = orderRepository.findAll(pageable);

//        List<Order> orders = page.getContent();
        print(all.getContent());

    }

    @Test
    public void name8() throws Exception {

        Metamodel metamodel = entityManager.getMetamodel();

        EntityType<Item> entity = metamodel.entity(Item.class);

        Set<Attribute<Item, ?>> declaredAttributes = entity.getDeclaredAttributes();
        declaredAttributes.stream().forEach(itemAttribute -> {
            System.out.println(itemAttribute.getName());
        });

        Attribute<? super Item, ?> itemId = entity.getAttribute("itemId");

        System.out.println(itemId.getName());

    }

    @Test
    public void name9() throws Exception {
        //创建CriteriaBuilder安全查询工厂
        //CriteriaBuilder是一个工厂对象,安全查询的开始.用于构建JPA安全查询.
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //创建CriteriaQuery安全查询主语句
        //CriteriaQuery对象必须在实体类型或嵌入式类型上的Criteria 查询上起作用。
        CriteriaQuery<Item> query = criteriaBuilder.createQuery(Item.class);
        //Root 定义查询的From子句中能出现的类型
        Root<Item> from = query.from(Item.class);
        //Predicate 过滤条件 构建where字句可能的各种条件
        Predicate like = criteriaBuilder.like(from.get(Item_.itemName), "%小%");
        query.where(like);
        query.orderBy(criteriaBuilder.asc(from.get(Item_.itemPrice)));
        TypedQuery<Item> query1 = entityManager.createQuery(query);
        List<Item> resultList = query1.getResultList();


        print(resultList);
    }

    @Test
    public void name10() throws Exception {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
//        ListJoin<Order, OrderItem> join = root.join(Order_.orderItems);

        query.where(criteriaBuilder.between(root.get(Order_.createTime), new Date("2017/07/31"), new Date("2017/08/20")));

        TypedQuery<Order> query1 = entityManager.createQuery(query);
        List<Order> resultList = query1.getResultList();
        print(resultList);
    }

    @Test
    public void name11() throws Exception {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
//        ListJoin<Order, OrderItem> join = root.join(Order_.orderItems);
        List<Predicate> predicatesList = new ArrayList<>();
        query.where(criteriaBuilder.between(root.get(Order_.createTime), new Date("2017/07/31"), new Date("2017/08/20")));

        TypedQuery<Order> query1 = entityManager.createQuery(query);
        List<Order> resultList = query1.getResultList();
        print(resultList);
    }

    @Test
    public void name12() throws Exception {
        List<Item> items = findByConditions("车", 200, null);
        print(items);
    }


    /**
     * 联合查询
     * @param name
     * @param price
     * @param stock
     * @return
     */
    private List<Item> findByConditions(String name, Integer price, Integer stock) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> query = criteriaBuilder.createQuery(Item.class);
        Root<Item> itemRoot = query.from(Item.class);
        List<Predicate> predicatesList = new ArrayList<>();
        if (name != null) {
            predicatesList.add(criteriaBuilder.and(criteriaBuilder.like(itemRoot.get(Item_.itemName), "%" + name + "%")));
        }
        if (price != null) {
            predicatesList.add(criteriaBuilder.and(criteriaBuilder.le(itemRoot.get(Item_.itemPrice), price)));
        }
        if (stock != null) {
            predicatesList.add(criteriaBuilder.and(criteriaBuilder.ge(itemRoot.get(Item_.itemStock), stock)));
        }

        query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
        TypedQuery<Item> typedQuery = entityManager.createQuery(query);
        List<Item> resultList = typedQuery.getResultList();
        return resultList;
    }

    public void print(Object obj) {
        System.out.println(JSONObject.toJSONString(obj, true));
    }


}
