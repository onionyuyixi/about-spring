package com.yuyixi.aboutspring;

import com.yuyixi.aboutspring.eventbusintergration.EventBusSupport;
import com.yuyixi.aboutspring.eventbusintergration.event.OnionEvent;
import com.yuyixi.aboutspring.eventbusintergration.event.YuyixiEvent;
import com.yuyixi.aboutspring.guava.EventBusFacade;
import com.yuyixi.aboutspring.guava.event.TestEvent;
import com.yuyixi.aboutspring.guava.smsdemo.Order;
import com.yuyixi.aboutspring.guava.smsdemo.TestOrderService;
import com.yuyixi.aboutspring.java.event.MyEvent;
import com.yuyixi.aboutspring.java.publisher.MyEventPublisher;
import lombok.experimental.Delegate;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    EventBusSupport<OnionEvent> eventBusSupport;

    @Test
    public void testEventPublish() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("one", 1);
        OnionEvent event = new OnionEvent(jsonObject);
        event.setSrc("123");
        event.setDesc("消息快快飞");
        eventBusSupport.post(event);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("one", 2);
        OnionEvent event1 = new OnionEvent(jsonObject1);
        event1.setSrc("123");
        event1.setDesc("消息快快飞");
        eventBusSupport.post(event1);
    }




    @Test
    public void testEventPublishAsync() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("one", 1);
        OnionEvent event = new OnionEvent(jsonObject);
        event.setSrc("123");
        event.setDesc("消息快快飞");
        eventBusSupport.postAsync(event);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("one", 2);
        OnionEvent event1 = new OnionEvent(jsonObject1);
        event1.setSrc("123");
        event1.setDesc("消息快快飞");
        eventBusSupport.postAsync(event1);
    }


    @Autowired
    EventBusSupport<YuyixiEvent> yuyixiEventBusSupport;

    @Test
    public void testYuyixi() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("one", "yuyixi");
        YuyixiEvent event = new YuyixiEvent(jsonObject);
        event.setDesc("123");
        event.setNo(1);
        yuyixiEventBusSupport.postAsync(event);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("one", "与乙烯");
        YuyixiEvent event1 = new YuyixiEvent(jsonObject1);
        event1.setNo(2);
        event1.setDesc("消息快快飞");
        yuyixiEventBusSupport.post(event1);
    }




}
