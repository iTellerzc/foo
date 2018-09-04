package com.iteller.foo.concurrent.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 18060903 on 2018/9/4.
 */
public class Business {

    private int count;

    private Lock lock = new ReentrantLock();

    public void service(){
        lock.lock();
        try {
            count ++;
            Thread.sleep(1);
            System.out.println("count:" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
