package com.iteller.foo.concurrent.lock.rw;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by 18060903 on 2018/9/3.
 */
public class RWDictionary{
    private final Map<String, Object> m = new TreeMap();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock rl = rwl.readLock();
    private final Lock wl = rwl.writeLock();

    public Object get(String key){
        rl.lock();
        try {
            return m.get(key);
        }finally{
            rl.unlock();
        }
    }

    public Object[] allKeys(){
        rl.lock();
        try {
            return m.keySet().toArray();
        }finally {
            rl.unlock();
        }
    }

    public Object put(String key, Object value){
        wl.lock();
        try{
            return m.put(key, value);
        }finally {
            wl.unlock();
        }
    }

    public void clear(){
        wl.lock();
        try{
            m.clear();
        }finally {
            wl.unlock();
        }
    }
}
