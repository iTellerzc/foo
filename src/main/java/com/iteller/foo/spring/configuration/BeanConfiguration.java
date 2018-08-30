package com.iteller.foo.spring.configuration;

import com.iteller.foo.spring.aop.AspectSample;
import com.iteller.foo.spring.aop.HelloService;
import com.iteller.foo.spring.aop.impl.HelloServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by 18060903 on 2018/8/9.
 */
@EnableAspectJAutoProxy
@Configuration
public class BeanConfiguration {

    @Bean
    public AspectSample getAspectSample(){
        return new AspectSample();
    }

    @Bean
    public HelloService getHelloService(){
        return new HelloServiceImpl();
    }
}
