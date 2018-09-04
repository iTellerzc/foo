package com.iteller.foo.concurrent.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 18060903 on 2018/9/4.
 */
public class MyExecutorScheduler {
    public static void main(String[] args){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("task just run one time!");
            }
        }, 5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("task will keeping running!");
            }
        }, 4, 2, TimeUnit.SECONDS);
    }
}
