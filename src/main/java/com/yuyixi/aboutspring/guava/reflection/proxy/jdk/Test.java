package com.yuyixi.aboutspring.guava.reflection.proxy.jdk;

import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) throws Throwable {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        HelloProxy helloProxy = new HelloProxy(new HelloImpl());

        Hello bind = (Hello) helloProxy.bind();

        bind.sayHello();

    }
}
