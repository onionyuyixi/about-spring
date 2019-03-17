package com.yuyixi.aboutspring.eventbusintergration.eventpublish;


import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.yuyixi.aboutspring.eventbusintergration.BaseEvent;
import com.yuyixi.aboutspring.eventbusintergration.EventBusSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventPublish<E extends BaseEvent> {

    @Autowired
    EventBusSupport eventBusSupport;

    public void pushlishEvent(E e){
        System.err.println("发布事件=======:"+e);
        eventBusSupport.post(e);
    }
    public void pushlishEventAsync(E e){
        System.err.println("异步发布事件=======:"+e);
        eventBusSupport.post(e);
    }

}
