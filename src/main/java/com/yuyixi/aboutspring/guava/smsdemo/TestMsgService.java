package com.yuyixi.aboutspring.guava.smsdemo;


import org.springframework.stereotype.Component;

@Component
public class TestMsgService {


    public void sendPhoneMsg(Order data) {

        System.err.println("发送手机消息-------" + data);

    }


    public void sendWebMsg(Order data) {
        System.err.println("发送网页消息-------" + data);
    }
}
