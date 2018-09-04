package com.iteller.foo.concurrent.lock.rw;


import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by 18060903 on 2018/9/3.
 */
public class RWLockExample {
    Object data;
    volatile  boolean cacheValid;
    //boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    void processCachedData(){
        rwl.readLock().lock();
        if(!cacheValid){
            //释放读锁获取写锁
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            try {
                //再次检查状态
                if(!cacheValid){
                    data = "This is read write lock!";
                    cacheValid = true;
                }else{
                    data = "cache data";
                }
                //降级为读锁
                rwl.readLock().lock();
            }finally {
                rwl.writeLock().unlock();
            }
        }
        try{
            System.out.println("data:" + data);
        }finally {
            rwl.readLock().unlock();
        }
    }

    void processCachedDataNoLock(){
        //再次检查状态
        if(!cacheValid){
            data = "This is read write lock!";
            cacheValid = true;
        }else{
            data = "cache data";
        }
        System.out.println("data:" + data);
    }


    public static void main(String[] args){
        RWLockExample rwLockExample = new RWLockExample();
        Thread t1 = new Thread(new MyThread(rwLockExample));
        Thread t2 = new Thread(new MyThread(rwLockExample));
        t1.start();
        t2.start();
        //rwLockExample.processCachedData();
        rwLockExample.processCachedDataNoLock();
    }
}
