package com.simpletour.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DynamicUpdate
@DynamicInsert
public class Item implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private int itemId;
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

        if (itemId != item.itemId) return false;
        if (itemName != null ? !itemName.equals(item.itemName) : item.itemName != null) return false;
        if (itemPrice != null ? !itemPrice.equals(item.itemPrice) : item.itemPrice != null) return false;
        if (itemStock != null ? !itemStock.equals(item.itemStock) : item.itemStock != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (itemPrice != null ? itemPrice.hashCode() : 0);
        result = 31 * result + (itemStock != null ? itemStock.hashCode() : 0);
        return result;
    }
}
