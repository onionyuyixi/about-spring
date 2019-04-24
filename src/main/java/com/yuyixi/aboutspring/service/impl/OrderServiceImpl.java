package com.yuyixi.aboutspring.service.impl;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.yuyixi.aboutspring.entity.Orders;
import com.yuyixi.aboutspring.event.DecreaseStockEvent;
import com.yuyixi.aboutspring.eventbusintergration.eventpublish.EventPublish;
import com.yuyixi.aboutspring.repository.OrderRepository;
import com.yuyixi.aboutspring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EventPublish eventPublish;


    @Override
    public void addOrder(Orders order) {
        List<Integer> list = IntStream.range(0, 100).boxed().collect(Collectors.toList());
        orderRepository.save(order);

        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        list.parallelStream().forEach(a -> service.submit(() -> {
            Orders orders = Orders.builder()
                    .goodsId(order.getGoodsId())
                    .id(a.longValue() + 1)
                    .orderNo(order.getOrderNo())
                    .userName(order.getUserName())
                    .build();
            orderRepository.save(orders);
            DecreaseStockEvent event = new DecreaseStockEvent(order.getGoodsId());
            eventPublish.pushlishEventAsync(event);
        }));


    }
}
