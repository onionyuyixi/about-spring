package com.yuyixi.aboutspring.event;

import com.yuyixi.aboutspring.eventbusintergration.BaseEvent;
import lombok.Data;

@Data
public class DecreaseStockEvent extends BaseEvent {
    public DecreaseStockEvent(Object payLoad) {
        super(payLoad);
    }
}
