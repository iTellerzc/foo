package com.iteller.foo.concurrent.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 18060903 on 2018/8/3.
 */
public class TicketLock {

    private AtomicInteger serviceNum = new AtomicInteger();

    private AtomicInteger ticketNum = new AtomicInteger();

    private static final ThreadLocal<Integer> LOCAL = new ThreadLocal<Integer>();

    public void lock(){
        int myticket = ticketNum.getAndIncrement();
        LOCAL.set(myticket);
        while (myticket != serviceNum.get()){

        }
    }

    public void unlock(){
        int myticket = LOCAL.get();
        serviceNum.compareAndSet(myticket, myticket + 1);
    }
}
