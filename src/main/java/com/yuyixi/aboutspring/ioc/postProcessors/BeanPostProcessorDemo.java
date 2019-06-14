package com.yuyixi.aboutspring.ioc.postProcessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanPostProcessorDemo implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("BeanPostProcessor   --------------    before");
        if (bean instanceof Demo4Instance) {
            Demo4Instance demo4Instance = ((Demo4Instance) bean);
            demo4Instance.setName("123");
            System.err.println("this is  name is " + 123);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("BeanPostProcessor   --------------    after");
        return bean;
    }


}
