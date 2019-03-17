package com.yuyixi.aboutspring;

import com.yuyixi.aboutspring.event.MyEvent;
import com.yuyixi.aboutspring.publisher.MyEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AboutSpringApplication {
    

    public static void main(String[] args) {

        SpringApplication.run(AboutSpringApplication.class, args);
    }

}
