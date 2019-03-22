package com.yuyixi.aboutspring.controller;

import com.yuyixi.aboutspring.entity.Orders;
import com.yuyixi.aboutspring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("add")
    public Object addUser(@RequestBody Orders orders){
        try {
            orderService.addOrder(orders);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @GetMapping("test")
    public Object test(){
        for (int i = 0; i < 1000; i=i+2) {
            Orders orders = Orders.builder().id(new Long(i)).userName(i + "---order").orderNo("order--" + i)
                    .goodsId(new Long(1)).build();
            orderService.addOrder(orders);
        }
        return true;
    }

}
