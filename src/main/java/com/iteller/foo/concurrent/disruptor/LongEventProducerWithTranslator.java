package com.iteller.foo.concurrent.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by 18060903 on 2018/8/15.
 */
public class LongEventProducerWithTranslator {

    /**
     * 一个translator可以看成是事件初始化器
     */
    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
        public void translateTo(LongEvent longEvent, long sequence, ByteBuffer byteBuffer) {
            longEvent.setValue(byteBuffer.get(0));
        }
    };

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb){
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}