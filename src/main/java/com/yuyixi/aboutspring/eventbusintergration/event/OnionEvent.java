package com.yuyixi.aboutspring.eventbusintergration.event;

import com.yuyixi.aboutspring.eventbusintergration.BaseEvent;
import lombok.Data;

@Data
public class OnionEvent extends BaseEvent {

    private String src;
    private String desc;

    public OnionEvent(Object payLoad) {
        super(payLoad);
    }

    public OnionEvent(String src, Object payLoad, String desc) {
        super(payLoad);
        this.src = src;
        this.desc = desc;
    }

    @Override
    public Object getPayLoad() {
        return super.getPayLoad();
    }

    @Override
    public void setPayLoad(Object payLoad) {
        super.setPayLoad(payLoad);
    }
}
