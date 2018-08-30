package com.iteller.foo.concurrent;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 18060903 on 2018/7/17.
 */
public class TestArrayBlockingQueue {

    private BlockingQueue<String> bq = new ArrayBlockingQueue<String>(1);

    @Test
    public void testAdd() throws InterruptedException {
        bq.add("iTeller_zc");
        System.out.println(bq.size());
        bq.put("iTeller_zc2");
        System.out.println(bq.size());
    }
}
