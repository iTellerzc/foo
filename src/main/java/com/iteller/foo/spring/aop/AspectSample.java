package com.iteller.foo.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by 18060903 on 2018/8/9.
 */
@Aspect
public class AspectSample {

    /**
     * 定义切点， 标记方法
     */
    @Pointcut("execution(* com.iteller.foo.spring.aop.impl.HelloServiceImpl.sayHello(..))")
    public void aopInvoke(){
    }

    /**
     *切点之前调用
     */
    @Before("aopInvoke()")
    public void before(){
        System.out.println("before sayhello invoke!");
    }

    /**
     * 切点之后调用
     */
    @After("aopInvoke()")
    public void  after(){
        System.out.println("after sayHello invoke!");
    }

    /**
     *
     * @param pjp
     * @throws Throwable
     */
    @Around(value = "aopInvoke()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        System.out.println("invoke result : " + result);
    }
}
