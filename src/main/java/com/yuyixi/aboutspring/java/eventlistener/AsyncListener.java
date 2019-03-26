package com.yuyixi.aboutspring.java.eventlistener;


import com.yuyixi.aboutspring.java.event.MyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncListener {

    @EventListener
    @Async
    public void eventAsyncListner(MyEvent event){
        System.err.println("异步监听:"+event);
        System.err.println("异步监听:"+event.getMsg());
    }
}
