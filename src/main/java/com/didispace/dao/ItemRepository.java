package com.didispace.dao;

import com.didispace.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer>,JpaSpecificationExecutor<Item> {

    List<Item> findByItemNameContaining(String name);

    Item findByItemNameOrderByItemIdDesc(String name);



}
