package com.iteller.foo.concurrent.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by 18060903 on 2018/8/3.
 */
public class CLHLock {

    public static class CLHNode{
        private volatile boolean isLocked = true;
    }

    private volatile CLHNode tail;

    private static final ThreadLocal<CLHNode> LOCAL = new ThreadLocal<CLHNode>();

    private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class, "tail");

    public void lock(){
        CLHNode node = new CLHNode();
        LOCAL.set(node);
        CLHNode preNode = UPDATER.getAndSet(this, node);
        if(preNode != null){
            while (preNode.isLocked){

            }
            preNode = null;
            LOCAL.set(node);
        }
    }

    public void unlock(){
        CLHNode node = LOCAL.get();
        if(!UPDATER.compareAndSet(this, node, null)){
            node.isLocked = false;
        }
        node = null;
    }
}
