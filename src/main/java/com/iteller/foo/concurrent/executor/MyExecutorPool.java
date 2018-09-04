package com.iteller.foo.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 18060903 on 2018/9/4.
 */
public class MyExecutorPool {

    public static void main(String[] args){
        //ExecutorService executorService = Executors.newFixedThreadPool(3);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=1; i<=10; i++){
            //向线程池提交10个任务
            final int sequence = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for(int i=1; i<=2; i++){
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(String.format("%s is serving %d task loop of %d.",
                                Thread.currentThread().getName(), sequence, i));
                    }
                }
            });
        }
        System.out.println("all task has committed!");
        executorService.shutdown();
    }
}
