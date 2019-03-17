package com.yuyixi.aboutspring;

import com.yuyixi.aboutspring.event.MyEvent;
import com.yuyixi.aboutspring.publisher.MyEventPublisher;
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
    public void testMyEventPublisher(){
        MyEvent myEvent = new MyEvent("yuyixi", "今天要加班");
        myEventPublisher.publishEvent(myEvent);
    }


}
