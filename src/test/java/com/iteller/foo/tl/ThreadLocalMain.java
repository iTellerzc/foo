package com.iteller.foo.tl;

import java.util.Random;

/**
 * Created by 18060903 on 2018/9/3.
 */
public class ThreadLocalMain {

    public static void main(String[] args){
        Thread t = null;
        for(int i = 0; i < 2; i++){
            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    int currentRandom = new Random().nextInt();
                    System.out.println(String.format("%s--%d", Thread.currentThread().getName(), currentRandom));
                    ThreadShareData tsd = ThreadShareData.getCurrentThreadInstance();
                    tsd.setAge(currentRandom);
                    tsd.setName(String.format("name:%d", currentRandom));
                    //输出两个模块的值
                    new ClientA().get();
                    new ClientB().get();
                }
            });
            t.start();
        }
    }
}
