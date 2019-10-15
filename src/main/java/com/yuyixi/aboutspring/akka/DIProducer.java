package com.yuyixi.aboutspring.akka;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author yangcong
 * @date 2019/10/15 14:36
 */

public class DIProducer implements IndirectActorProducer {
    private ApplicationContext context;
    private String beanName;

    public DIProducer(ApplicationContext context, String beanName) {
        this.context = context;
        this.beanName = beanName;
    }

    @Override
    public Actor produce() {
        return (Actor) context.getBean(beanName);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) context.getType(beanName);
    }
}