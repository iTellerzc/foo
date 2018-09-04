package com.iteller.foo.concurrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by 18060903 on 2018/9/4.
 */
public class MySemaphore {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore sp = new Semaphore(3);
        for(int i=0; i<10; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        sp.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.format("线程%s进入，当前已有%d个并发！", Thread.currentThread().getName(), 3-sp.availablePermits()));
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(String.format("线程[%s]已离开---bye bye!", Thread.currentThread().getName()));
                    sp.release();
                }
            });
        }
        while(true){
            if(sp.availablePermits() == 3){
                System.out.println("所有线程已执行完毕！");
                break;
            }
        }
        executorService.shutdown();
    }
}
