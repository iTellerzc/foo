package com.iteller.foo.spring.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 18060903 on 2018/8/9.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {com.iteller.foo.spring.configuration.BeanConfiguration.class})
public class TestAspect {

    @Autowired
    private HelloService helloService;

    @Test
    public void testSayHello(){
        helloService.sayHello("iTeller_zc");
    }
}
