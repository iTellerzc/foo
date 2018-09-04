package com.iteller.foo.concurrent.lock.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 18060903 on 2018/9/4.
 */
public class MyLock {

    public static void main(String[] args){
        final Business business = new Business();
        ExecutorService executorService = null;
        try{
            executorService = Executors.newFixedThreadPool(3);
            for(int i = 0; i<300; i++){
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        business.service();
                    }
                });
            }
        }finally {
            if(executorService != null){
                executorService.shutdown();
            }
        }

    }
}
