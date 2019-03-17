package com.yuyixi.aboutspring.eventlistener;

import com.yuyixi.aboutspring.event.MyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(Integer.MIN_VALUE)//第一种的指定listener方法  需要实现ApplicationListener接口
public class MyEventListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        System.err.println("接受到的事件"+event);
        System.err.println("接受到的消息"+event.getMsg());
    }
}
