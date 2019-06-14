package com.yuyixi.aboutspring.ioc.postProcessors;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = {"com.yuyixi.aboutspring.ioc.postProcessors.*"})
public class MainTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.refresh();
        context.getBean(Demo4Instance.class);
    }
}
