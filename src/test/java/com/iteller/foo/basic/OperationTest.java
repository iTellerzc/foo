package com.iteller.foo.basic;

import org.junit.Test;

/**
 * Created by 18060903 on 2018/9/3.
 */
public class OperationTest {

    @Test
    public void test1(){
        int countBits = Integer.SIZE - 3;
        System.out.println(countBits);
        System.out.println(1<<countBits);
    }
}
