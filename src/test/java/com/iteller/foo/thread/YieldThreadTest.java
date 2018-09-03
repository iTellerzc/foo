package com.iteller.foo.thread;

/**
 * Created by 18060903 on 2018/8/31.
 */
public class YieldThreadTest {

    public static void main(String[] args){
        YieldThread t1 = new YieldThread("th1");
        YieldThread t2 = new YieldThread("th2");
        t1.start();
        t2.start();
    }
}
