package com.yuyixi.aboutspring;

import org.junit.Test;

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
}
