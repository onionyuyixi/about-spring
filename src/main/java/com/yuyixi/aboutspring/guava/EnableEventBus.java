package com.yuyixi.aboutspring.guava;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Configuration
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EventBusConfiguration.class)
@Documented
public @interface EnableEventBus {

}
