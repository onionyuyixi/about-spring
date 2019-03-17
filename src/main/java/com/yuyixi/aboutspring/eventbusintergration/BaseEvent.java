package com.yuyixi.aboutspring.eventbusintergration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class BaseEvent {

    Object payLoad;



}
