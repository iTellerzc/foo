package com.iteller.foo.concurrent.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * 事件消费者
 * Created by 18060903 on 2018/8/15.
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("rec data value :" + longEvent.getValue());
    }
}
