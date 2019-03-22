package com.yuyixi.aboutspring.controller;

import com.yuyixi.aboutspring.entity.User;
import com.yuyixi.aboutspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public Object addUser(@RequestBody User user){
        try {
            userService.addUser(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
