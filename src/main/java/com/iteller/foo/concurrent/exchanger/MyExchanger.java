package com.iteller.foo.concurrent.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 18060903 on 2018/9/4.
 */
public class MyExchanger {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Exchanger exchanger = new Exchanger();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep((long)(Math.random() * 5000));
                    String ownData = "牛肉";
                    System.out.println(String.format("商贩[%s]准备置换[%s]", Thread.currentThread().getName(), ownData));
                    String recvData = (String)exchanger.exchange(ownData);
                    System.out.println(String.format("商贩[%s]置换回[%s]", Thread.currentThread().getName(), recvData));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep((long)(Math.random() * 5000));
                    String ownData = "money";
                    System.out.println(String.format("顾客[%s]准备置换[%s]", Thread.currentThread().getName(), ownData));
                    String recvData = (String)exchanger.exchange(ownData);
                    System.out.println(String.format("顾客[%s]置换回[%s]", Thread.currentThread().getName(), recvData));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown();
    }
}
