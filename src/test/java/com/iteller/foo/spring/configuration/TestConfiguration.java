package com.iteller.foo.spring.configuration;

import com.iteller.foo.spring.aop.AspectSample;
import com.iteller.foo.spring.aop.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 18060903 on 2018/8/9.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {com.iteller.foo.spring.configuration.BeanConfiguration.class})
public class TestConfiguration {

    @Autowired
    private HelloService helloService;

    @Autowired
    private AspectSample aspectSample;

    @Test
    public void testBean(){
        System.out.println(helloService != null);
        System.out.println(aspectSample != null);
    }
}
