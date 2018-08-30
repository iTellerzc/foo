package com.iteller.foo.concurrent.queue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 18060903 on 2018/7/17.
 */
public class TestProducerConsumer {

    @Test
    public void testQueue() throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
        UUIDProducer p = new UUIDProducer(queue);
        new Thread(p).start();
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        new Thread(c1).start();
        new Thread(c2).start();
        Thread.sleep(1000*5);
    }

    @Test
    public void testA(){
        System.out.println("iTeller_zc");
    }
}
