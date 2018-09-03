package com.iteller.foo.atomic;

/**
 * Created by 18060903 on 2018/9/3.
 */
public class CommonTest {

    private volatile int count = 0;

    public synchronized void increment(){
        count++;
    }

    public int getCount(){
        return count;
    }
}
