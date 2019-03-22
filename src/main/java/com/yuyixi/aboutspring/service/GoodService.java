package com.yuyixi.aboutspring.service;

import com.yuyixi.aboutspring.entity.Good;

public interface GoodService {

    void addGoods(Good good);

    void decreaseStock(Long id);
}
