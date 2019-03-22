package com.yuyixi.aboutspring.controller;

import com.yuyixi.aboutspring.entity.Good;
import com.yuyixi.aboutspring.entity.User;
import com.yuyixi.aboutspring.service.GoodService;
import com.yuyixi.aboutspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/good/")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @PostMapping("add")
    public Object addUser(@RequestBody Good good){
        try {
            goodService.addGoods(good);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
