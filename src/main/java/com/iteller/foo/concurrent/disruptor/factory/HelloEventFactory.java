package com.iteller.foo.concurrent.disruptor.factory;

import com.iteller.foo.concurrent.disruptor.event.HelloEvent;
import com.lmax.disruptor.EventFactory;

/**
 * Created by 18060903 on 2018/8/30.
 */
public class HelloEventFactory implements EventFactory<HelloEvent> {
    @Override
    public HelloEvent newInstance() {
        return new HelloEvent();
    }
}
