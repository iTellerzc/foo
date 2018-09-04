package com.iteller.foo.concurrent.tl;

import org.junit.Test;

/**
 * Created by 18060903 on 2018/6/12.
 */
public class TestThreadId {

    @Test
    public void testGet(){
        System.out.println(ThreadId.get());
        System.out.println(ThreadId.get());
    }
}
