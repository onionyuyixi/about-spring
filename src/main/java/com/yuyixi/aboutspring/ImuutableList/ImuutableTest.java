package com.yuyixi.aboutspring.ImuutableList;

import com.google.common.collect.Lists;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author yangcong
 * @date 2019/8/16 9:55
 */

public class ImuutableTest {


    @Test
    public void test1() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        Lock lock = new ReentrantLock();

        IntStream.range(0, 100000).forEach(list1::add);

        long start = System.currentTimeMillis();
        IntStream.range(0, 10000000).parallel().forEach(list2::add); //List并不是线程安全的  所以不可靠
        System.err.println("并行执行用时" + (System.currentTimeMillis() - start));

        IntStream.range(0, 100000).forEach(i -> {
            lock.lock();
            try {
                list3.add(i);
            } finally {
                lock.unlock();
            }
        });

        System.out.println("串行执行的大小：" + list1.size());
        System.out.println("并行执行的大小：" + list2.size());
        System.out.println("加锁并行执行的大小：" + list3.size());

    }


    @Test
    public void test2() {

        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

        long start = System.currentTimeMillis();
        IntStream.range(0, 10000000).parallel().forEach(queue::add);
        System.err.println("用时" + (System.currentTimeMillis() - start)); //用时太长
        System.out.println("并行执行--采用线程安全性数据：" + queue.size()); //操作安全 可靠

    }


    @Test
    public void test3() {
        List<Integer> list = Lists.newArrayList();
        long start = System.currentTimeMillis();
        Flux.range(0, 10000000)
                .parallel()
                .subscribe(a -> list.add(a));
        System.err.println("用时" + (System.currentTimeMillis() - start)); //用时比queue少
        System.out.println("采用响应式 并行：" + list.size()); //操作安全 可靠
    }

    @Test
    public void test4() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            List<Integer> list = Lists.newArrayList();
            Flux.range(0, 10000000)
                    .buffer(2147483647)//默认的最大buffer 同于buffer()
                    .parallel(2 * (Runtime.getRuntime().availableProcessors()))
                    .flatMap(integers -> Flux.fromIterable(integers))
                    .subscribe(a -> list.add(a));
            System.err.println(Thread.currentThread().getName()+"用时" + (System.currentTimeMillis() - start)); //速度最快
            System.out.println("采用响应式 并行 带有最大buffer：" + list.size());
        }
        System.err.println("test4用时" + (System.currentTimeMillis() - start)); //速度最快
    }


    @Test
    public void test5() {
        List<Integer> list = Lists.newArrayList();
        long start = System.currentTimeMillis();
        Flux.range(0, 10000000)
                .buffer(2147483647 / 2)
                .parallel()
                .flatMap(integers -> Flux.fromIterable(integers)).
                subscribe(a -> list.add(a));
        System.err.println("用时" + (System.currentTimeMillis() - start));
        System.out.println("采用响应式 并行 带有小buffer：" + list.size());
    }


    @Test
    public void test6() {
        List<Integer> list = Lists.newArrayList();
        long start = System.currentTimeMillis();
        Flux.range(0, 10000000)
                .buffer(2147483647)//默认的最大buffer 同于buffer
                .flatMap(integers -> Flux.fromIterable(integers)).
                subscribe(a -> list.add(a));
        System.err.println("用时" + (System.currentTimeMillis() - start)); //速度最快
        System.out.println("采用响应式 带有最大buffer：" + list.size());
    }


    @Test
    public void test7() {
        for (int i = 0; i < 20; i++) {
            ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue<>();
            long start = System.currentTimeMillis();
            Flux.range(0, 10000000)
                    .buffer(2147483647)//默认的最大buffer 同于buffer
                    .parallel()
                    .flatMap(integers -> Flux.fromIterable(integers)).
                    subscribe(a -> queue.add(a));
            System.err.println("用时" + (System.currentTimeMillis() - start)); //速度最快
            System.out.println("采用响应式 并行 且使用queue 带有最大buffer：" + queue.size());
        }
    }

}
