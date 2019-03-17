package com.yuyixi.aboutspring.eventbusintergration.eventSubcribe;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.yuyixi.aboutspring.eventbusintergration.BaseEvent;
import org.springframework.stereotype.Component;

@Component
public class EventSubcribe<E extends BaseEvent> {


    @Subscribe
    @AllowConcurrentEvents
    public void subcribeEven(E e) {
        System.err.println("我接受到了消息" + e.getPayLoad());
    }


}
