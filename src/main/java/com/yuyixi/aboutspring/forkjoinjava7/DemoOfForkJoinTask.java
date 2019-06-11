package com.yuyixi.aboutspring.forkjoinjava7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class DemoOfForkJoinTask {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.err.println("是否是异步模式:"+forkJoinPool.getAsyncMode());
        ForkJoinTask<String> forkJoinTask = new ForkJoinTask<String>() {
            @Override
            public String getRawResult() {
                return "java 7 forkjoin";
            }

            @Override
            protected void setRawResult(String value) {

            }

            @Override
            protected boolean exec() {
                return true;//如果返回结果是false 则线程将一直处于等待中
            }
        };

        String invoke = forkJoinPool.invoke(forkJoinTask);
        forkJoinPool.shutdown();
        System.err.println("结果"+invoke);

    }
}
