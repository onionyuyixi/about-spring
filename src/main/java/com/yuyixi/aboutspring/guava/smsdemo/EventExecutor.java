package com.yuyixi.aboutspring.guava.smsdemo;

import java.util.function.Consumer;

public interface EventExecutor<S> {
    void executeEvent(Consumer<S> executor);
}
