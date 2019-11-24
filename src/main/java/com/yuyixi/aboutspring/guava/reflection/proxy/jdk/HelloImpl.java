package com.yuyixi.aboutspring.guava.reflection.proxy.jdk;

public class HelloImpl implements Hello{

    @Override
    public void sayHello() {
        System.err.println("hello jdk proxy");
    }
}
