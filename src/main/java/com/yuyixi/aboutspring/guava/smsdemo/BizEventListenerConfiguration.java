package com.yuyixi.aboutspring.guava.smsdemo;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@BizEventListener
public class BizEventListenerConfiguration {

    @Autowired
    private TestMsgService msgService;

    @Subscribe
    public void executeEvent(BizEvent bizEvent) {
        switch (bizEvent.getEventType()) {
            // 订单创建，发送短信，.......
            case ORDER_CREATE:
                bizEvent.executeEvent((data) -> msgService.sendPhoneMsg((Order) data));
                break;
            // 订单修改，站内信提醒，.......
            case ORDER_UPDATE:
                bizEvent.executeEvent((data) -> msgService.sendWebMsg((Order) data));
                break;

            default:
                bizEvent.executeEvent((data) -> log.info("executeEvent bizEventData = {}", data));
        }
    }

}
