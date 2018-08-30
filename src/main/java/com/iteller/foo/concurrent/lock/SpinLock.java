package com.iteller.foo.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by 18060903 on 2018/8/3.
 */
public class SpinLock {

    private AtomicReference<Thread> sign = new AtomicReference<Thread>();

    public void lock(){
        Thread thread = Thread.currentThread();
        while (!sign.compareAndSet(null, thread)){

        }
    }

    public void unlock(){
        Thread current = Thread.currentThread();
        sign.compareAndSet(current, null);
    }
}
