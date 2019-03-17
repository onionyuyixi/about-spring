package com.yuyixi.aboutspring.guava.smsdemo;

public interface BizEventPublisher {

    /**
     * 发布同步事件
     *
     * @param eventData
     */
    void publishEvent(Object eventData);

    /**
     * 发布异步事件
     *
     * @param eventData
     */
    void publishEventAsync(Object eventData);
}
