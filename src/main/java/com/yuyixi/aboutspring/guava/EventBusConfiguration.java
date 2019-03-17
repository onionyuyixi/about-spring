package com.yuyixi.aboutspring.guava;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class EventBusConfiguration implements InitializingBean, DisposableBean {

    @Autowired
    private ApplicationContext applicationContext;

    private Map<String, EventAdapter> beans = null;

    @Override //初始化的InitializingBean接口
    public void afterPropertiesSet() {

        beans = applicationContext.getBeansOfType(EventAdapter.class);
        if (beans != null) {
            for (EventAdapter eventAbstract : beans.values()) {
                EventBusFacade.register(eventAbstract);
            }
        }
    }

    @Override //DisposableBean 用完销毁
    public void destroy() {
        if (beans != null) {
            for (EventAdapter eventAbstract : beans.values()) {
                EventBusFacade.unregister(eventAbstract);
            }
        }
    }

}
