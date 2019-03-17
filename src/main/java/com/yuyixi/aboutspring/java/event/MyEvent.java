package com.yuyixi.aboutspring.java.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;


/**
 * ApplicationEvent 里自带时间戳
 */
@Data
public class MyEvent extends ApplicationEvent {
    private String msg;

    public MyEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }
}
