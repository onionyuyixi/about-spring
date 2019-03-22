package com.yuyixi.aboutspring.eventbusintergration.event;

import com.yuyixi.aboutspring.eventbusintergration.BaseEvent;
import lombok.Data;


@Data
public class YuyixiEvent extends BaseEvent {

    private String desc;

    private int no;

    public YuyixiEvent(Object payLoad) {
        super(payLoad);
    }


}
