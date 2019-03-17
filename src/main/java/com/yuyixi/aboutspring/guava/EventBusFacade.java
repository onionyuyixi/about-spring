package com.yuyixi.aboutspring.guava;


import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventBusFacade {

    private final static EventBus eventBus = new EventBus();


    public static void post(BaseEvent event) {
        execute(event);
    }


    public static void execute(BaseEvent event) {
        if (event == null) {
            return;
        }
        eventBus.post(event);
    }


    public static void register(EventAdapter<? extends BaseEvent> handler) {
        if (handler == null) {
            return;
        }
        eventBus.register(handler);
        log.info("Registered eventAdapter class: {}", handler.getClass());
    }


    public static void unregister(EventAdapter<? extends BaseEvent> handler) {
        if (handler == null) {
            return;
        }
        eventBus.unregister(handler);
        log.info("Unregisted eventAdapter class: {}", handler.getClass());
    }

}
