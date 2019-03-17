package com.yuyixi.aboutspring.eventbusintergration.eventSubcribe;

import com.google.common.eventbus.Subscribe;
import com.yuyixi.aboutspring.eventbusintergration.BaseEvent;
import org.springframework.stereotype.Component;

@Component
public class EventSubcribe {


    @Subscribe
    public void subcribeEven(BaseEvent event){
        System.err.println("我接受到了消息"+event.getPayLoad());
    }


}
