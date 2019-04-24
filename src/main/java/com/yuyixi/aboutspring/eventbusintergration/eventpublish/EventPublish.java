package com.yuyixi.aboutspring.eventbusintergration.eventpublish;


import com.yuyixi.aboutspring.eventbusintergration.BaseEvent;
import com.yuyixi.aboutspring.eventbusintergration.EventBusSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class EventPublish<E extends BaseEvent> {

    @Autowired
    EventBusSupport eventBusSupport;

    AtomicInteger sum = new AtomicInteger(0);
    public void pushlishEvent(E e){
        System.err.println("发布事件=======:"+e);
        eventBusSupport.post(e);
    }
    public void pushlishEventAsync(E e){
        System.err.println(sum.incrementAndGet()+"cishu");
        System.err.println("异步发布事件=======:"+e);
        eventBusSupport.postAsync(e);
    }

}
