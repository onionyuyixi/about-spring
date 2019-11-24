package com.yuyixi.aboutspring.guava.reflection.proxy.guava;

import com.google.common.reflect.Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Test {


    public static void main(String[] args) {

        JobHandler jobHandler = new JobHandler(new JobImpl());
        Job job = Reflection.newProxy(Job.class, jobHandler);
        job.doJob();
    }
}
