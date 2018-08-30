package com.iteller.foo.concurrent.disruptor.event;

import java.io.Serializable;

/**
 * Created by 18060903 on 2018/8/30.
 */
public class HelloEvent implements Serializable{

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
