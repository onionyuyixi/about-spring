package com.yuyixi.aboutspring.guava;


import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

@Slf4j
public abstract class EventAdapter<E extends BaseEvent> {

    private static final String METHOD_NAME = "process";


    @Subscribe
    public void onEvent(E event) {
        if (ReflectionUtils.findMethod(this.getClass(), METHOD_NAME, event.getClass()) != null) {
            try {
                if (!process(event)) {
                    log.warn("handle event {} fail", event.getClass());
                }
            } catch (Exception e) {
                log.error(String.format("handle event %s exception", event.getClass()), e);
            }
        }
    }

    public abstract boolean process(E e);
}
