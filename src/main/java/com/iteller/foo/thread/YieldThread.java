package com.iteller.foo.thread;

/**
 * Created by 18060903 on 2018/8/31.
 */
public class YieldThread extends Thread{

    public YieldThread(String name){
        super(name);
    }

    @Override
    public void run() {
       for(int i = 1; i <= 50; i++){
           System.out.println(this.getName() + ":" + i);
           if(i == 30){
               this.yield();
           }
       }
    }

    public static void main(String[] args){
        new YieldThread("YieldThread").start();
    }
}

