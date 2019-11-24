package com.yuyixi.aboutspring.guava.reflection.proxy.guava;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@AllArgsConstructor
public class JobHandler implements InvocationHandler {

    Object object;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(object, args);

        return invoke;
    }
}
