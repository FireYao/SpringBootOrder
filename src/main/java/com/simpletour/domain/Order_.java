package com.simpletour.domain;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@StaticMetamodel(Order.class)
public class Order_ {
    public static volatile SingularAttribute<Order, Integer> orderId;
    public static volatile SingularAttribute<Order, Integer> userId;
    public static volatile SingularAttribute<Order, Integer> amout;
    public static volatile SingularAttribute<Order, Integer> stauts;
    public static volatile SingularAttribute<Order, Date> createTime;
    public static volatile SingularAttribute<Order, Date> updateTime;
    public static volatile SingularAttribute<Order, Date> dealTime;
    public static volatile ListAttribute<Order, OrderItem> orderItems;
}
