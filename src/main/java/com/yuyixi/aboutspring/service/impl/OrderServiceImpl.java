package com.yuyixi.aboutspring.service.impl;

import com.google.common.util.concurrent.*;
import com.yuyixi.aboutspring.entity.Orders;
import com.yuyixi.aboutspring.event.DecreaseStockEvent;
import com.yuyixi.aboutspring.eventbusintergration.eventpublish.EventPublish;
import com.yuyixi.aboutspring.repository.OrderRepository;
import com.yuyixi.aboutspring.service.GoodService;
import com.yuyixi.aboutspring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EventPublish eventPublish;

    @Autowired
    private GoodService goodService;

    @Override
    public void addOrder(Orders order) {
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
        addOrder.addListener(() -> goodService.decreaseStock(order.getGoodsId()),service);
    }
}
