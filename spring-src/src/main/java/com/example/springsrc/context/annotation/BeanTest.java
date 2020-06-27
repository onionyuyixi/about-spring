package com.example.springsrc.context.annotation;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Consumer;

@Component("beanTest")
public class BeanTest {


    // 可以指定profile
    @Bean
    @Lazy
    @Scope
    @Primary
    @Profile("dev")
    public Onion getOnion() {
        return new Onion("onion");
    }

    public static void main(String[] args) {
        Consumer<Object> consumer = System.err::println;
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(context);
        beanDefinitionReader.registerBean(Onion.class);
        System.err.println(context.getId());
        context.setDisplayName("onion");
        ApplicationContext parent = context.getParent();
        System.err.println(Optional.ofNullable(parent).isPresent());
        ConfigurableEnvironment environment = context.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();
        for (String activeProfile : activeProfiles) {
            System.err.println(activeProfile);
        }
        MutablePropertySources propertySources = environment.getPropertySources();
        Iterator<PropertySource<?>> iterator = propertySources.iterator();
        while (iterator.hasNext()) {
            PropertySource<?> next = iterator.next();
            System.err.println(next.getName() + "-----");
            Object source = next.getSource();
            String jsonString = JSONObject.toJSONString(source);
            Map map = JSONObject.parseObject(jsonString, Map.class);
            map.entrySet().forEach(System.err::println);
        }
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.err.println(beanDefinitionName);
        }

        Collection<ApplicationListener<?>> applicationListeners = context.getApplicationListeners();

        applicationListeners.forEach(consumer);

        List<BeanFactoryPostProcessor> processors = context.getBeanFactoryPostProcessors();
        processors.forEach(consumer);

        System.err.println(context.getStartupDate());



    }

    @AllArgsConstructor
    private class Onion {
        String name;
    }
}