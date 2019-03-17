package com.yuyixi.aboutspring;

import com.yuyixi.aboutspring.eventbusintergration.event.OnionEvent;
import com.yuyixi.aboutspring.eventbusintergration.eventpublish.EventPublish;
import com.yuyixi.aboutspring.guava.EventBusFacade;
import com.yuyixi.aboutspring.guava.event.TestEvent;
import com.yuyixi.aboutspring.guava.smsdemo.Order;
import com.yuyixi.aboutspring.guava.smsdemo.TestOrderService;
import com.yuyixi.aboutspring.java.event.MyEvent;
import com.yuyixi.aboutspring.java.publisher.MyEventPublisher;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AboutSpringApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    MyEventPublisher myEventPublisher;

    @Test
    public void testMyEventPublisher() {
        MyEvent myEvent = new MyEvent("yuyixi", "今天要加班");
        myEventPublisher.publishEvent(myEvent);
    }

    @Test
    public void testExecute() {

        EventBusFacade.execute(new TestEvent()); //发布事件

    }


    @Autowired
    private TestOrderService testOrderService;


    @Test
    public void testOrder() {
        testOrderService.create(new Order(123456789L, 902038493L));
        testOrderService.update(new Order(123456789L, 902038495L));
    }


    @Autowired
    EventPublish eventPublish;

    @Test
    public void testEventPublish() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("one", 1);
        jsonObject.put("two", 2);
        jsonObject.put("three", 3);
        jsonObject.put("four", 4);
        OnionEvent event = new OnionEvent(jsonObject);
        event.setSrc("123");
        event.setDesc("消息快快飞");
        eventPublish.pushlishEvent(event);
    }



}
