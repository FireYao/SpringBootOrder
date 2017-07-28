package com.didispace.dao;

import com.didispace.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {

    List<Item> findByItemNameContaining(String name);

    @Modifying
    @Query("update Item i set i.itemStock = :stock where i.itemId = :itemId")
    void updateItemStock(@Param("itemId") int itemId, @Param("stock") int stock);

}
