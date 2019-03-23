package com.yuyixi.aboutspring.service.impl;

import com.google.common.collect.Streams;
import com.google.common.util.concurrent.*;
import com.yuyixi.aboutspring.entity.Orders;
import com.yuyixi.aboutspring.event.DecreaseStockEvent;
import com.yuyixi.aboutspring.eventbusintergration.eventpublish.EventPublish;
import com.yuyixi.aboutspring.repository.OrderRepository;
import com.yuyixi.aboutspring.service.GoodService;
import com.yuyixi.aboutspring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EventPublish eventPublish;

    @Autowired
    private GoodService goodService;

    @Override
    public void addOrder(Orders order) throws ExecutionException, InterruptedException {
        List<Integer> list = IntStream.range(0, 1000).boxed().collect(Collectors.toList());
        orderRepository.save(order);
        DecreaseStockEvent event = new DecreaseStockEvent(order.getGoodsId());
        eventPublish.pushlishEventAsync(event);
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<?> addOrder = service.submit(() -> {
            Orders orders = Orders.builder()
                    .goodsId(order.getGoodsId())
                    .id(order.getId() + 1)
                    .orderNo(order.getOrderNo())
                    .userName(order.getUserName())
                    .build();
            orderRepository.save(orders);
        });
        long l1 = System.currentTimeMillis();
        for (Integer integer : list) {
            addOrder.addListener(() -> goodService.decreaseStock(order.getGoodsId()), service);
        }
//        if (addOrder.isDone()) {
        System.err.println(System.currentTimeMillis() - l1 + "guava");
//        }

        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
            Orders orders = Orders.builder()
                    .goodsId(order.getGoodsId())
                    .id(order.getId() + 2)
                    .orderNo(order.getOrderNo())
                    .userName(order.getUserName())
                    .build();
            orderRepository.save(orders);
        });

        new Thread(() -> {
            goodService.decreaseStock(order.getGoodsId());
//            runAsync.
        }).start();
        //此法不通
        CompletableFuture<Void> compose = runAsync.
                thenCompose(aVoid -> CompletableFuture.runAsync(() -> goodService.decreaseStock(order.getGoodsId())));
        compose.get();

        long l = System.currentTimeMillis();
        for (Integer integer : list) {
            runAsync.thenRunAsync(new Thread(() -> {
                goodService.decreaseStock(order.getGoodsId());
//            runAsync.
            }));
        }
        System.err.println(System.currentTimeMillis() - l + "java8");

        //此法亦不通
        CompletableFuture<Void> handle = runAsync.handle((BiFunction<Void, Throwable, Void>) (aVoid, throwable) -> {
            goodService.decreaseStock(order.getGoodsId());
            return null;
        });
        //此法可通
        handle.thenRunAsync(new Thread(() -> {
            goodService.decreaseStock(order.getGoodsId());
//            runAsync.
        }));
    }
}
