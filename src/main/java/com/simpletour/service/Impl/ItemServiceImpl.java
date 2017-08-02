package com.simpletour.service.Impl;

import com.simpletour.dao.ItemRepository;
import com.simpletour.domain.Item;
import com.simpletour.domain.Item_;
import com.simpletour.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemRepository itemRepository;

    @Resource
    private EntityManager entityManager;

    @Override
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

    @Override
    public void delete(int itemId) {
        itemRepository.delete(itemId);
    }

    @Override
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


    @Override
    public List<Item> findByConditions(String name, Integer price, Integer stock) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> query = criteriaBuilder.createQuery(Item.class);
        Root<Item> itemRoot = query.from(Item.class);
        List<Predicate> predicatesList = new ArrayList<>();

        predicatesList.add(criteriaBuilder.or(
                criteriaBuilder.like(itemRoot.get(Item_.itemName), "%" + name + "%"),
                criteriaBuilder.le(itemRoot.get(Item_.itemPrice), price),
                criteriaBuilder.ge(itemRoot.get(Item_.itemStock), stock)));
        query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
        TypedQuery<Item> typedQuery = entityManager.createQuery(query);
        List<Item> resultList = typedQuery.getResultList();
        return resultList;
    }

    @Override
    public void updateItemStock(int itemId, int buyNum) {
        Item one = itemRepository.findOne(itemId);
        int stock = one.getItemStock() - buyNum;
        if (stock < 0)
            throw new RuntimeException("库存不足");
        one.setItemStock(stock);
        itemRepository.save(one);
    }
}
