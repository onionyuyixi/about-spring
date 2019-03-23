package com.yuyixi.aboutspring.java8future;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class Java8CompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //简单的异步执行任务
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> System.err.println("11"+getThreadName()));
        runAsync.get();

        //简单的异步有返回结果的任务
        CompletableFuture supplyAsync = CompletableFuture.supplyAsync((Supplier) () -> {
            System.err.println("开始异步的有返回结果的任务");
            return "22";
        });
        System.err.println(supplyAsync.get());


        //任务的转换 相当于guava的 listennerFuture.addListenerFuture()的串联任务
        CompletableFuture<String> string = CompletableFuture.supplyAsync(() -> "33");
        CompletableFuture<Integer> string2Integer = string.handleAsync((s, throwable) -> Integer.valueOf(s));
        System.err.println("string2Integer:" + string2Integer.get());
        CompletableFuture<Long> string2Long = string2Integer.handleAsync((s, throwable) -> Long.valueOf(s));
        System.err.println("string2Long:" + string2Long.get());

        //从下面的循环结果看 两个打印 都只在控制台出现一次 而且1000个结果都1133 这说明逻辑的结果只发生一次 不会重复确定 而数据只是逻辑的提现
        CompletableFuture<Long> future = string.handleAsync((s, throwable) -> {
                    System.err.println("string to integer");
                    return Integer.valueOf(s) + new Integer(100);
                }
        ).handleAsync((a, b) -> {
            System.err.println(a instanceof Integer);
            System.err.println("xxxxxxxxxxxxxxxx");
            return Long.valueOf(a) + new Long(1000);
        });
//        System.err.println(future.get());
        for (Integer i = 0; i < 1000; i++) {
            Integer finalI = i;
            Runnable runnable = () -> {
                try {
                    System.err.println(future.get() + "---------" + finalI.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            };
            runnable.run();
        }
        String s = "33";
        AtomicReference<Integer> i = new AtomicReference<>(0);
        AtomicReference<Long> l = new AtomicReference(0);
        //callable
        Callable<Integer> str2Int = () -> {
            System.err.println("字符串变整数");
            i.set(Integer.valueOf(s));
            return i.get();
        };
        Callable<Long> int2Long = () -> {
            System.err.println("整数变成长整型");
            l.set(Long.valueOf(i.get()));
            return l.get();
        };
        for (Integer k = 0; k < 1000; k++) {
            Integer finalI = k;
            Runnable runnable = () -> {
                try {
                    System.err.println(str2Int.call().intValue());
                    System.err.println(int2Long.call().longValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            runnable.run();
        }

        //线程次与callable

        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> submit = service.submit(str2Int);
        Future<Long> longFuture = service.submit(int2Long);
        System.err.println(longFuture.get() + "call--线程池");


        FutureTask<Integer> integerFutureTask = new FutureTask(str2Int);
        integerFutureTask.run(); //必须要有该方法 去执行具体的callable内容 FutureTask里封装了 volatile 的thread
        integerFutureTask.isDone();
        System.err.println(integerFutureTask.get() + " FutureTask ");


        CompletableFuture<String> fortyFour = CompletableFuture.supplyAsync(() -> "44");
        CompletableFuture<Integer> integerCompletableFuture = fortyFour.thenApplyAsync(a -> Integer.valueOf(a));
        System.err.println(integerCompletableFuture.get());


        CompletableFuture<String> fiftyFive = CompletableFuture.supplyAsync(() -> "55");
        //两个结果的合并 即如 c = a + b 需要 a b 都完成后 才有结果
        CompletableFuture<Integer> 异步合并 = fiftyFive.thenCombineAsync(
                CompletableFuture.runAsync(() -> {
                    System.err.println("异步合并");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }),

                (s1, aVoid) -> Integer.parseInt(s1)
        );
        System.err.println(异步合并.get());

        //这个就是链式接构了
        CompletableFuture<String> seventySeven = CompletableFuture.supplyAsync(() -> "77");
        CompletableFuture<String> composeAsync = seventySeven.thenComposeAsync(
                s12 -> CompletableFuture.supplyAsync(()->{
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.err.println("我要成88了"+Thread.currentThread().getName());
                    return s12 + "yes bingo"+Thread.currentThread().getName();
                })
                , Executors.newSingleThreadExecutor());
        System.err.println(composeAsync.get()+Thread.currentThread().getName());
    }

   static String getThreadName(){
        return Thread.currentThread().getName();
    }
}