package com.iteller.foo.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Test;


/**
 * Created by 18060903 on 2018/6/11.
 */
public class TestConcurrentHashMap {

    @Test
    public void testPutIfAbsent(){
        Map<String, String> cmap = new ConcurrentHashMap<String, String>();
        cmap.put("name", "iTeller_zc");
        System.out.println(cmap.get("name"));
        cmap.putIfAbsent("name", "tracy");
        System.out.println(cmap.get("name"));
    }
}
