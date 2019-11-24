package com.yuyixi.aboutspring.akka;

import akka.actor.AbstractActor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author yangcong
 * @date 2019/10/15 14:41
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().matchAny(o -> System.err.println("接受到消息：" + o)).build();
    }

}
