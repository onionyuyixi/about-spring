package com.yuyixi.aboutspring.eventbusintergration.eventSubcribe;

import com.google.common.eventbus.Subscribe;
import com.yuyixi.aboutspring.event.DecreaseStockEvent;
import com.yuyixi.aboutspring.eventbusintergration.BaseEvent;
import com.yuyixi.aboutspring.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventSubcribe<E extends BaseEvent> {

    @Autowired
    private GoodService goodService;

    @Subscribe
    public void subcribeEven(E e) {
        System.err.println("我接受到了消息" + e.getPayLoad());
        if (e instanceof DecreaseStockEvent) {
            DecreaseStockEvent event = (DecreaseStockEvent) e;
            goodService.decreaseStock(((Long) event.getPayLoad()));
        }

    }


}
