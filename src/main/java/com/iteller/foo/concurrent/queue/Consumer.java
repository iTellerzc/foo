package com.iteller.foo.concurrent.queue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by 18060903 on 2018/7/17.
 */
public class Consumer implements Runnable{
    private final BlockingQueue queue;

    public Consumer(BlockingQueue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(true){
                consume(queue.take());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void consume(Object o){
        System.out.println("consume value : " + o);
    }
}
