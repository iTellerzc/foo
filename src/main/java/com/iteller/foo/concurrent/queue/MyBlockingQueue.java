package com.iteller.foo.concurrent.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 18060903 on 2018/9/4.
 */
public class MyBlockingQueue {

    public static void main(String[] args){
        final BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        for(int i=0; i<2; i++){
            new Thread(){
                @Override
                public void run(){
                    while(true){
                        try{
                            Thread.sleep((long)(Math.random() * 3000));
                            System.out.println(String.format("%s准备放入队列...", Thread.currentThread().getName()));
                            blockingQueue.put("1");
                            System.out.println(String.format("%s已放入队列，目前队列大小%d", Thread.currentThread().getName(), blockingQueue.size()));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }.start();

            new Thread(){
                @Override
                public void run(){
                    while(true){
                        try{
                            Thread.sleep(3000);
                            System.out.println(String.format("%s准备从队列取数据...", Thread.currentThread().getName()));
                            blockingQueue.take();
                            System.out.println(String.format("%s已取出队列，目前队列大小%d", Thread.currentThread().getName(), blockingQueue.size()));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
    }
}
