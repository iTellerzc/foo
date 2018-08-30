package com.iteller.foo.concurrent.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by 18060903 on 2018/8/15.
 */
public class LongEventProducer {

    private RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    /**
     * 用来发布事件，参数会通过事件传递给消费者
     * @param bb
     */
    public void onData(ByteBuffer bb){
        long sequence = ringBuffer.next();
        System.out.println("seq:" + sequence);
        try{
            LongEvent longEvent = ringBuffer.get(sequence);
            System.out.println("value :" + bb.get(0));
            longEvent.setValue(bb.get(0));
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
