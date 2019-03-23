package com.yuyixi.aboutspring.service;

import com.yuyixi.aboutspring.entity.Orders;

import java.util.concurrent.ExecutionException;

public interface OrderService {

    void addOrder(Orders order) throws ExecutionException, InterruptedException;
}
