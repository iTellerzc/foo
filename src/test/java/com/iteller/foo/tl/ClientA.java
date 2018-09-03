package com.iteller.foo.tl;

/**
 * Created by 18060903 on 2018/9/3.
 */
public class ClientA {

    public void get(){
        ThreadShareData tsa = ThreadShareData.getCurrentThreadInstance();
        System.out.println(String.format("clientA name is:%s \t data.name is:%s \t data.age is :%s",
                Thread.currentThread().getName(), tsa.getName(), tsa.getAge()));
    }
}
