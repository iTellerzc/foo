package com.iteller.foo.concurrent.cyclebarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 18060903 on 2018/9/4.
 */
public class MyCycleBarrier {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for(int i=0; i<3; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long)(Math.random() * 5000));
                        System.out.println(String.format("线程[%s]已到达集合地点1，当前已有%d在等待！", Thread.currentThread().getName(), cyclicBarrier.getNumberWaiting()+1));
                        cyclicBarrier.await();

                        Thread.sleep((long)(Math.random() * 3000));
                        System.out.println(String.format("线程[%s]已到达集合地点2，当前已有%d在等待！", Thread.currentThread().getName(), cyclicBarrier.getNumberWaiting()+1));
                        cyclicBarrier.await();

                        Thread.sleep((long)(Math.random() * 1000));
                        System.out.println(String.format("线程[%s]已到达集合地点3，当前已有%d在等待！", Thread.currentThread().getName(), cyclicBarrier.getNumberWaiting()+1));
                        cyclicBarrier.await();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        }
        executorService.shutdown();
    }
}
