package com.iteller.foo.concurrent.disruptor.handler;

import com.iteller.foo.concurrent.disruptor.event.HelloEvent;
import com.lmax.disruptor.EventHandler;

/**
 * Created by 18060903 on 2018/8/30.
 */
public class HelloEventHandler implements EventHandler<HelloEvent> {

    @Override
    public void onEvent(HelloEvent helloEvent, long l, boolean b) throws Exception {
        System.out.println("receive data:" + helloEvent.getValue());
    }
}
