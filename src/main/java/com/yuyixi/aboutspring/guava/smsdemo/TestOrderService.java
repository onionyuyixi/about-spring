package com.yuyixi.aboutspring.guava.smsdemo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//这里的OrderService没有直接依赖MsgService
@Slf4j
@Component
public class TestOrderService {


    @Autowired
    private BizEventPublisher bizEventPublisher;

    public void create(Order order) {
        log.info("创建订单", order);
        bizEventPublisher.publishEvent(BizEvent.of(BizEventType.ORDER_CREATE, order));
    }

    public void update(Order order) {
        log.info("修改订单", order);
        bizEventPublisher.publishEventAsync(BizEvent.of(BizEventType.ORDER_UPDATE, order));
    }

}
