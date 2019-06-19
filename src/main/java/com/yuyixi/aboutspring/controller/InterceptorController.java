package com.yuyixi.aboutspring.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interceptorcontroller/")
public class InterceptorController {


    @GetMapping("getTest")
    public Integer getTest() {
        return 1;
    }


}
