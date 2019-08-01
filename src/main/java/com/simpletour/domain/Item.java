package com.simpletour.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@DynamicUpdate
@DynamicInsert
public class Item implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Integer itemId;
    private String itemName;
    private Integer itemPrice;
    private Integer itemStock;


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "item_name")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "item_price")
    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Basic
    @Column(name = "item_stock")
    public Integer getItemStock() {
        return itemStock;
    }

    public void setItemStock(Integer itemStock) {
        this.itemStock = itemStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemId, item.itemId) &&
                Objects.equals(itemName, item.itemName) &&
                Objects.equals(itemPrice, item.itemPrice) &&
                Objects.equals(itemStock, item.itemStock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName, itemPrice, itemStock);
    }
}
