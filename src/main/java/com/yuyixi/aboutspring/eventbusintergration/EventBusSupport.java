
package com.yuyixi.aboutspring.eventbusintergration;

public interface EventBusSupport<E extends BaseEvent> {

    void post(E event);

    void postAsync(E event);

}
