package com.yuyixi.aboutspring.guava.smsdemo;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class BizEventPublisherConfiguration implements BizEventPublisher {

    /**
     * 同步事件总线
     */
    private EventBus eventBus = new EventBus();
    /**
     * 异步事件总线
     */
    private AsyncEventBus eventBusAsync = new AsyncEventBus(
            new ThreadPoolExecutor(
                    Runtime.getRuntime().availableProcessors(),
                    Runtime.getRuntime().availableProcessors() * 2,
                    5L,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(),
                    new ThreadFactoryBuilder().setNameFormat("guava-event-executor-pool-%d").build()
            ),
            (Throwable e, SubscriberExceptionContext exceptionContext) -> {
                log.error("", e);
            }
    );

    @Override
    public void publishEvent(Object eventData) {
        eventBus.post(eventData);
    }

    @Override
    public void publishEventAsync(Object eventData) {
        eventBusAsync.post(eventData);
    }

    /**
     * 构造器，注册监听者
     *
     * @param beanFactory
     */
    public BizEventPublisherConfiguration(ListableBeanFactory beanFactory) {
        // 获取所有带有 @BizEventListener 的 bean，将他们注册为监听者
        beanFactory.getBeansWithAnnotation(BizEventListener.class)
                .forEach((beanName, listener) -> {
                    eventBusAsync.register(listener);
                    eventBus.register(listener);
                });
    }

}
