package com.yuyixi.aboutspring.guava.smsdemo;

import lombok.Data;

@Data
public class Order {
    private long orderId;
    private long userId;

    public Order(long orderId, long userId) {
        this.setOrderId(orderId);
        this.setUserId(userId);
    }
}