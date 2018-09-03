package com.iteller.foo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 18060903 on 2018/9/3.
 */
public class AtomicIntegerTest {

    private AtomicInteger count = new AtomicInteger();

    public synchronized void increment(){
        count.incrementAndGet();
    }

    public int getCount(){
        return count.get();
    }
}
