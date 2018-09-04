package com.iteller.foo.concurrent.tl;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 18060903 on 2018/6/12.
 */
public class ThreadId {
    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };

    public static int get() {
        return threadId.get();
    }
}
