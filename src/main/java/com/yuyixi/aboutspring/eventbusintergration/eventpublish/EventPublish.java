package com.yuyixi.aboutspring.eventbusintergration.eventpublish;


import com.google.common.eventbus.EventBus;
import com.yuyixi.aboutspring.eventbusintergration.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventPublish {

    @Autowired
    EventBus eventBus;

    public void pushlishEvent(BaseEvent event){
        System.err.println("发布事件=======:"+event);
        eventBus.post(event);
    }

}
