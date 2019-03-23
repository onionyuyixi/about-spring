package com.yuyixi.aboutspring.controller;

import com.yuyixi.aboutspring.entity.Orders;
import com.yuyixi.aboutspring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("add")
    public Object addUser(@RequestBody Orders orders) {
        try {
            orderService.addOrder(orders);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @GetMapping("test")
    public Object test() throws ExecutionException, InterruptedException {
//        for (int i = 0; i < 1000; i=i+3) {
        Orders orders = Orders.builder().id(new Long(1)).userName(1 + "---order").orderNo("order--" + 1)
                .goodsId(new Long(1)).build();
        orderService.addOrder(orders);
//        }
        return true;
    }

}
