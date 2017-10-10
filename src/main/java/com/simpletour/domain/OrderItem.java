package com.simpletour.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "order_item", schema = "public", catalog = "myLocalDatabase")
public class OrderItem implements Serializable {
    private Integer orderId;
    private Integer itemId;
    private Integer itemNums;
    private String itemName;
    private Integer itemPrice;

    @Id
    @GeneratedValue
    @Column(name = "oi_id")
    private int oiId;

    @Basic
    @Column(name = "order_id")
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "item_id")
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "item_nums")
    public Integer getItemNums() {
        return itemNums;
    }

    public void setItemNums(Integer itemNums) {
        this.itemNums = itemNums;
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


    public int getOiId() {
        return oiId;
    }

    public void setOiId(int oiId) {
        this.oiId = oiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (oiId != orderItem.oiId) return false;
        if (orderId != null ? !orderId.equals(orderItem.orderId) : orderItem.orderId != null) return false;
        if (itemId != null ? !itemId.equals(orderItem.itemId) : orderItem.itemId != null) return false;
        if (itemNums != null ? !itemNums.equals(orderItem.itemNums) : orderItem.itemNums != null) return false;
        if (itemName != null ? !itemName.equals(orderItem.itemName) : orderItem.itemName != null) return false;
        if (itemPrice != null ? !itemPrice.equals(orderItem.itemPrice) : orderItem.itemPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (itemNums != null ? itemNums.hashCode() : 0);
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + (itemPrice != null ? itemPrice.hashCode() : 0);
        result = 31 * result + oiId;
        return result;
    }
}
