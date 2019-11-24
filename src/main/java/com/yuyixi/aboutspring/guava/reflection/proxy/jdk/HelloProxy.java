package com.yuyixi.aboutspring.guava.reflection.proxy.jdk;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@AllArgsConstructor
public class HelloProxy implements InvocationHandler {

    private Object object;

    public <T> T bind() {
        return (T) Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object invoke = method.invoke(object, args);

        return invoke;
    }
}
