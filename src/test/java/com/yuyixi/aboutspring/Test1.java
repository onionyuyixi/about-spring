package com.yuyixi.aboutspring;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;

/**
 * @author yangcong
 * @date 2019/8/31 22:39
 */

public class Test1 {


    @Test
    public void testA() {
        System.err.println(Integer.class.isAssignableFrom(Object.class));
        System.err.println(String.class.isAssignableFrom(String.class));
    }

    @Test
    public void test2() {
        HashSet<Object> set = Sets.newHashSet();
        System.err.println(set.add(1));
        System.err.println(set.add(1));


    }
}
