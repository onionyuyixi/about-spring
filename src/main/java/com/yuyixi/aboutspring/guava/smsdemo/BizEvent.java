package com.yuyixi.aboutspring.guava.smsdemo;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Consumer;

@Data
@AllArgsConstructor
public class BizEvent<S> implements EventExecutor<S> {

    private BizEventType eventType;
    private S data;

    @Override
    public void executeEvent(Consumer<S> executor) {
        executor.accept(data);
    }

    public static<S> BizEvent of(BizEventType eventType, S data) {
        return new BizEvent(eventType, data);
    }


}
