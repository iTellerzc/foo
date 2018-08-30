package com.iteller.foo.concurrent.disruptor.main;

import com.iteller.foo.concurrent.disruptor.event.HelloEvent;
import com.iteller.foo.concurrent.disruptor.factory.HelloEventFactory;
import com.iteller.foo.concurrent.disruptor.handler.HelloEventHandler;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 18060903 on 2018/8/30.
 */
public class DisruptorMain {

    public static void main(String[] args){

        //线程池
        ExecutorService executor = Executors.newFixedThreadPool(1);

        //等待策略
        WaitStrategy blockingWaitStrategy = new BlockingWaitStrategy();

        WaitStrategy sleepingWaitStrategy = new SleepingWaitStrategy();

        WaitStrategy yieldingWaitStrategy = new YieldingWaitStrategy();

        //事件工程
        EventFactory<HelloEvent> eventFactory = new HelloEventFactory();
        //RingBuffer大小，必须是2的N次方
        int ringBufferSize = 1024 * 1024;
        //创建Disruptor
        Disruptor<HelloEvent> disruptor = new Disruptor<HelloEvent>(eventFactory, ringBufferSize, executor, ProducerType.SINGLE, blockingWaitStrategy);
        //事件处理器
        EventHandler<HelloEvent> eventHandler = new HelloEventHandler();
        disruptor.handleEventsWith(eventHandler);
        //启动disruptor
        disruptor.start();

        //获取RingBuffer
        RingBuffer<HelloEvent> ringBuffer = disruptor.getRingBuffer();
        //请求下一个事件序号
        long sequence = ringBuffer.next();
        HelloEvent helloEvent = disruptor.get(sequence);
        //设置事件内容
        helloEvent.setValue("send data: hello world!");
        //发布事件
        ringBuffer.publish(sequence);

    }
}
