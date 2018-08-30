package com.iteller.foo.concurrent.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by 18060903 on 2018/8/15.
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
