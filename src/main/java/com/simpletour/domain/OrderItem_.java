package com.simpletour.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrderItem.class)
public class OrderItem_ {

    public static volatile SingularAttribute<OrderItem, Integer> itemId;
    public static volatile SingularAttribute<OrderItem, String> itemName;
    public static volatile SingularAttribute<OrderItem, Integer> itemPrice;
    public static volatile SingularAttribute<OrderItem, Integer> itemStock;
    public static volatile SingularAttribute<OrderItem, Integer> itemNums;
    public static volatile SingularAttribute<OrderItem, Integer> orderId;

}
