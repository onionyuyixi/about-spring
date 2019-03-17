package com.yuyixi.aboutspring.java.eventlistener;


import com.yuyixi.aboutspring.java.event.MyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class AnotherListener {

    @EventListener //这是第二种 指定方法 明显 更具有优势 灵活
    public void eventListener(MyEvent event) {
        System.err.println("----------------接受到的事件" + event);
        System.err.println("----------------接受到的消息" + event.getMsg());
    }
}
