package com.simpletour.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "tb_order", schema = "public", catalog = "myLocalDatabase")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private int orderId;
    private Integer userId;
    private Integer amout;
    private Integer stauts;

    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    @JSONField(format = "yyyy-MM-dd")
    private Date updateTime;

    @Column(name = "deal_time")
    @JSONField(format = "yyyy-MM-dd")
    private Date dealTime;

    @Transient
    private List<OrderItem> orderItems;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "amout")
    public Integer getAmout() {
        return amout;
    }

    public void setAmout(Integer amout) {
        this.amout = amout;
    }

    @Basic
    @Column(name = "stauts")
    public Integer getStauts() {
        return stauts;
    }

    public void setStauts(Integer stauts) {
        this.stauts = stauts;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (userId != null ? !userId.equals(order.userId) : order.userId != null) return false;
        if (amout != null ? !amout.equals(order.amout) : order.amout != null) return false;
        if (stauts != null ? !stauts.equals(order.stauts) : order.stauts != null) return false;
        if (createTime != null ? !createTime.equals(order.createTime) : order.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(order.updateTime) : order.updateTime != null) return false;
        if (dealTime != null ? !dealTime.equals(order.dealTime) : order.dealTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (amout != null ? amout.hashCode() : 0);
        result = 31 * result + (stauts != null ? stauts.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (dealTime != null ? dealTime.hashCode() : 0);
        return result;
    }
}
