package com.iteller.foo.concurrent.condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 18060903 on 2018/9/3.
 */
public class MyCondition {

    public static void main(String[] args){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        final ConditionBusiness business = new ConditionBusiness();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                business.sub();
            }
        });

        for(int i=0;i<5;i++){
            business.main();
        }

        executorService.shutdown();
    }
}
