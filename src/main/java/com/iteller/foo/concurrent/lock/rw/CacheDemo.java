package com.iteller.foo.concurrent.lock.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by 18060903 on 2018/9/4.
 */
public class CacheDemo {

    private Map<String, Object> cacheDatas = new HashMap<>(128);

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args){
        final CacheDemo cacheDemo = new CacheDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i = 1; i<=3; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(cacheDemo.get("" + 1));
                }
            });
        }
        executorService.shutdown();
    }

    public Object get(String id){
        Object value = null;
        rwl.readLock().lock();
        try{
            if(cacheDatas.get(id) == null){
                //升级为写锁
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    if(value == null){
                        value = "new value, not hit cache!";
                        cacheDatas.put(id, value);
                    }else{
                        System.out.println("hit another cache!");
                        value = cacheDatas.get(id);
                    }
                    rwl.readLock().lock();
                }finally {
                    rwl.writeLock().unlock();
                }
            }else{
                //命中缓存
                System.out.println("hit cache!");
                value = cacheDatas.get(id);
            }
        }finally {
            rwl.readLock().unlock();
        }
        return value;
    }
}
