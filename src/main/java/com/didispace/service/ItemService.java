package com.didispace.service;


import com.didispace.domain.Item;

import java.util.List;

public interface ItemService {

    void save(Item item);

    Item get(int itemId);

    List<Item> getItems();

    void delete(int itemId);

    void update(Item item);

    List<Item> findItemsByPrice(int price);

    List<Item> findByConditions(String name, Integer price, Integer stock);

    void updateItemStock(int itemId, int buyNum);
}
