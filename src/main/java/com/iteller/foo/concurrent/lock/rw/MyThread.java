package com.iteller.foo.concurrent.lock.rw;

/**
 * Created by 18060903 on 2018/9/3.
 */
public class MyThread implements Runnable {
    private RWLockExample example;

    public MyThread(RWLockExample example){
        this.example = example;
    }

    @Override
    public void run() {
        example.processCachedData();
    }
}
