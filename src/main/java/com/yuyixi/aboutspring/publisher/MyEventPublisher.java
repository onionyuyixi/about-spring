package com.yuyixi.aboutspring.publisher;

import com.yuyixi.aboutspring.event.MyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * 借助ApplicationContext来发布事件
 */
@Component
public class MyEventPublisher {

    @Autowired
    ApplicationContext context;


    public void publishEvent(MyEvent event) {
        System.err.println("准备发布事件" + event);
        context.publishEvent(event);
    }


}
