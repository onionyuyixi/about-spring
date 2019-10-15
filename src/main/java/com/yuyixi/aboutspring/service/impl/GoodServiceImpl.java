package com.yuyixi.aboutspring.service.impl;

import com.google.common.eventbus.Subscribe;
import com.yuyixi.aboutspring.entity.Good;
import com.yuyixi.aboutspring.repository.GoodRepository;
import com.yuyixi.aboutspring.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodRepository goodRepository;

    ReentrantLock lock = new ReentrantLock();

    @Override
    public void addGoods(Good good) {
        goodRepository.save(good);
    }

    @Subscribe
//    @AllowConcurrentEvents
    @Override
    public void decreaseStock(Long id) {
//        if(lock.tryLock()){
//            lock.lock();
            Optional<Good> byId = goodRepository.findById(id);
            if (byId.isPresent()){
                Good good = byId.get();
                AtomicInteger aLong = new AtomicInteger(good.getStocks());
                int l = aLong.decrementAndGet();
                good.setStocks(l);
                goodRepository.save(good);
            }
//            lock.unlock();
//        }

    }
}
