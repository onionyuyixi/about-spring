package com.yuyixi.aboutspring.guava.smsdemo;


import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

@Configuration
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Import(EventBusConfiguration.class)
@Documented
public @interface BizEventListener {
}
