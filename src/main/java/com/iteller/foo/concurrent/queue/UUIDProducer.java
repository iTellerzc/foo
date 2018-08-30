package com.iteller.foo.concurrent.queue;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 18060903 on 2018/7/17.
 */
public class UUIDProducer implements Runnable {
    private final BlockingQueue<String> bq;

    public UUIDProducer(BlockingQueue<String> queue){
        this.bq = queue;
    }

    public String produce(){
        String uuid = UUID.randomUUID().toString();
        System.out.println("produce value:" + uuid);
        return uuid;
    }

    public void run() {
        try {
            while (true){
                bq.put(produce());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
