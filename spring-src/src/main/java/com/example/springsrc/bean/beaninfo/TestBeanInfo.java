package com.example.springsrc.bean.beaninfo;

import lombok.Data;
import org.junit.Test;
import org.springframework.beans.ExtendedBeanInfoFactory;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class TestBeanInfo {


    @Test
    public void test1() {
        Class<BeanInfoDemo> demoClass = BeanInfoDemo.class;
        Method[] methods = demoClass.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            int nParams = method.getParameterTypes().length;
            Class<?> returnType = method.getReturnType();
            boolean assignableFrom = void.class.isAssignableFrom(returnType);
            boolean isWrite = methodName.length() > 3 &&
                    methodName.startsWith("set") &&
                    Modifier.isPublic(method.getModifiers()) &&
                    (!assignableFrom || Modifier.isStatic(method.getModifiers())) &&
                    (nParams == 1 || (nParams == 2 && int.class == parameterTypes[0]));
//            boolean isWrite = isCandidateWriteMethod(method);
            System.err.println(isWrite ? methodName + "------" + nParams + "参数" : "not set method");
        }


    }


    @Test
    public void test2() {
        ArrayList<String> strings = new ArrayList<>(2);
        System.err.println(strings.isEmpty());
    }


    @Test
    public void test3() {
        List<BeanInfoDemo> lists = new ArrayList<>(2);
        BeanInfoDemo[] arrays = new BeanInfoDemo[2];
        Map<Object, BeanInfoDemo> map = new LinkedHashMap<>(2);
        Class<BeanInfoDemo> type = BeanInfoDemo.class;

        Class<? extends List> listsClass = lists.getClass();
        Class<? extends BeanInfoDemo[]> arraysClass = arrays.getClass();
        Class<? extends Map> mapClass = map.getClass();
        System.err.println(Collection.class.isAssignableFrom(listsClass));
        System.err.println(arraysClass.isArray());
        System.err.println(Map.class.isAssignableFrom(mapClass));


    }

    public static boolean isCandidateWriteMethod(Method method) {
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        int nParams = parameterTypes.length;
        return (methodName.length() > 3 && methodName.startsWith("set") && Modifier.isPublic(method.getModifiers()) &&
                (!void.class.isAssignableFrom(method.getReturnType()) || Modifier.isStatic(method.getModifiers())) &&
                (nParams == 1 || (nParams == 2 && int.class == parameterTypes[0])));
    }


    @Data
    static class BeanInfoDemo {
        private static String name;
        private static int age;

        //解决了builder形式
        public BeanInfoDemo setName(String name) {
            this.name = name;
            return this;
        }

        public static void setAge(int num) {
            age = num;
        }
        public static void setAge(int num,String other) {
            age = num;
            System.err.println(other);
        }
    }

}
