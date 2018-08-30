package com.iteller.foo.spring.aop.impl;

import com.iteller.foo.spring.aop.HelloService;

/**
 * Created by 18060903 on 2018/8/9.
 */
public class HelloServiceImpl implements HelloService{

    public String sayHello(String name) {
        System.out.println("hello: " +  name + "!");
        return  name;
    }
}
