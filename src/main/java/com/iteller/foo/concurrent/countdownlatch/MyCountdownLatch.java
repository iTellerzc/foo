package com.iteller.foo.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 18060903 on 2018/9/4.
 */
public class MyCountdownLatch {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(3);
        for(int i=0; i<3; i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try{
                        System.out.println(String.format("线程[%s]准备接受起跑命令！", Thread.currentThread().getName()));
                        cdOrder.await();

                        System.out.println(String.format("线程[%s]冲刺中！", Thread.currentThread().getName()));
                        Thread.sleep((long)(Math.random() * 5000));

                        System.out.println(String.format("线程[%s]已到达终点！", Thread.currentThread().getName()));
                        cdAnswer.countDown();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnable);
        }


        //主线程
        try{
            Thread.sleep((long)(Math.random()*5000));

            System.out.println(String.format("裁判线程[%s]准备开始发布命令， 冲刺开枪！", Thread.currentThread().getName()));
            System.out.println(String.format("裁判线程[%s]已发布命令，等待比赛结果！", Thread.currentThread().getName()));
            cdOrder.countDown();
            cdAnswer.await();

            System.out.println(String.format("裁判线程[%s]已宣布比赛结果！", Thread.currentThread().getName()));
        }catch (Exception e){
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
