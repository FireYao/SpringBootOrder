package com.simpletour.service.Impl;

import com.simpletour.dao.ItemRepository;
import com.simpletour.domain.Item;
import com.simpletour.domain.Item_;
import com.simpletour.exception.ItemStockNotEnoughException;
import com.simpletour.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(
        readOnly = true
)
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemRepository itemRepository;

    @Resource
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Item item) {

        itemRepository.save(item);

    }

    @Override
    public Item get(int itemId) {
        return itemRepository.findOne(itemId);
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll().stream().filter(item -> item.getItemStock() > 0).sorted((o1, o2) -> o1.getItemId() - o2.getItemId()).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void delete(int itemId) {
        itemRepository.delete(itemId);
    }

    @Override
    @Transactional
    public void update(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> findItemsByPrice(int price) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> query = criteriaBuilder.createQuery(Item.class);
        Root<Item> itemRoot = query.from(Item.class);

        Predicate predicate = criteriaBuilder.ge(itemRoot.get(Item_.itemPrice), price);

        query.where(predicate);

        TypedQuery<Item> typedQuery = entityManager.createQuery(query);

        List<Item> resultList = typedQuery.getResultList();

        return resultList;
    }


    /**
     * 条件查询item
     * name price stock 联合查询
     *
     * @param name
     * @param price
     * @param stock
     * @return
     */
    @Override
    public List<Item> findByConditions(String name, Integer price, Integer stock) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> query = criteriaBuilder.createQuery(Item.class);
        Root<Item> itemRoot = query.from(Item.class);
        List<Predicate> predicatesList = new ArrayList<>();
        //name模糊查询 ,like语句
        if (name != null) {
            predicatesList.add(
                    criteriaBuilder.and(
                            criteriaBuilder.like(
                                    itemRoot.get(Item_.itemName), "%" + name + "%")));
        }

        // itemPrice 小于等于 <= 语句
        if (price != null) {
            predicatesList.add(
                    criteriaBuilder.and(
                            criteriaBuilder.le(
                                    itemRoot.get(Item_.itemPrice), price)));
        }
        //itemStock 大于等于 >= 语句
        if (stock != null) {
            predicatesList.add(
                    criteriaBuilder.and(
                            criteriaBuilder.ge(
                                    itemRoot.get(Item_.itemStock), stock)));
        }
        query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
        TypedQuery<Item> typedQuery = entityManager.createQuery(query);
        List<Item> resultList = typedQuery.getResultList();
        return resultList;
    }

    @Override
    @Transactional
    public void updateItemStock(int itemId, int buyNum) {
        Item one = itemRepository.findOne(itemId);
        int stock = one.getItemStock() - buyNum;
        if (stock < 0)
            throw new ItemStockNotEnoughException("库存不足");
        one.setItemStock(stock);
        itemRepository.save(one);
    }
}
