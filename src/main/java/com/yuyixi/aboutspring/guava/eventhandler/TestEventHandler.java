package com.yuyixi.aboutspring.guava.eventhandler;

import com.yuyixi.aboutspring.guava.EventAdapter;
import com.yuyixi.aboutspring.guava.event.TestEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class TestEventHandler extends EventAdapter<TestEvent> {
    @Override
    public boolean process(TestEvent testEvent) {
        log.info("搜到消息----"+ testEvent);
        return true;
    }
}
