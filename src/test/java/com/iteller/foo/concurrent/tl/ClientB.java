package com.iteller.foo.concurrent.tl;

/**
 * Created by 18060903 on 2018/9/3.
 */
public class ClientB {

    public void get(){
        ThreadShareData tsa = ThreadShareData.getCurrentThreadInstance();
        System.out.println(String.format("clientB name is:%s \t data.name is:%s \t data.age is :%s",
                Thread.currentThread().getName(), tsa.getName(), tsa.getAge()));
    }
}
