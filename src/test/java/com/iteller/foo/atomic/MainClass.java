package com.iteller.foo.atomic;

/**
 * Created by 18060903 on 2018/9/3.
 */
public class MainClass {
    public static void main(String[] args){
        final CommonTest commonTest = new CommonTest();
        final AtomicIntegerTest atomicIntegerTest = new AtomicIntegerTest();
        for(int i=0; i<3; i++){
            //各自同时开启三个线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0; j<10; j++){
                        System.out.println("common name:" + Thread.currentThread().getName() + " before add:" + commonTest.getCount());
                        commonTest.increment();
                        System.out.println("common after add:" + commonTest.getCount());
                        System.out.println("atomic name:" + Thread.currentThread().getName() + " before add:" + atomicIntegerTest.getCount());
                        atomicIntegerTest.increment();
                        System.out.println("atomic after add:" + atomicIntegerTest.getCount());
                        /*try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    }
                }
            }).start();
        }

        //读取线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++){
                    System.out.println(Thread.currentThread().getName() + "-->" + commonTest.getCount());
                    System.out.println(Thread.currentThread().getName() + "-->" + atomicIntegerTest.getCount());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
