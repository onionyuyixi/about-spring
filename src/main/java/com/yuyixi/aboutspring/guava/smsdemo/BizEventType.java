package com.yuyixi.aboutspring.guava.smsdemo;

public enum BizEventType {

    ORDER_CREATE("订单-创建"),
    ORDER_UPDATE("订单-修改"),
    ;

    String describe;

    BizEventType(String describe) {
        this.describe = describe;
    }

}
