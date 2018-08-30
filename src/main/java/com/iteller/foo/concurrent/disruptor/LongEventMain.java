package com.iteller.foo.concurrent.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by 18060903 on 2018/8/15.
 */
public class LongEventMain {

    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        LongEventFactory longEventFactory = new LongEventFactory();
        int bufferSize = 1024 * 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<>(longEventFactory, bufferSize, executor);
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducer longEventProducer = new LongEventProducer(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        for(long i = 10; true; i++){
            bb = bb.putLong(0, i);
            System.out.println("bb index 0 value :" + bb.get(0));
            longEventProducer.onData(bb);
            Thread.sleep(1000);
        }

    }
}
