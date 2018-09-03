package com.iteller.foo.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 18060903 on 2018/9/3.
 */
public class ConditionBusiness {

    Lock lock = new ReentrantLock();

    //得到当前阻塞条件
    Condition condition = lock.newCondition();
    boolean isSub = true;

    public void sub(){
        lock.lock();
        if(!isSub){
            //不是sub执行条件，默认阻塞
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try{
            for(int i=0; i < 5; i++){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
            isSub = false;
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public void main(){
        lock.lock();
        if(isSub){
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try{
            for (int i=0; i<5; i++){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
            isSub = true;
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
