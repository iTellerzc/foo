package com.iteller.foo.concurrent.tl;

/**
 * Created by 18060903 on 2018/9/3.
 */
public class ThreadShareData {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private ThreadShareData(){

    }

    private static ThreadLocal<ThreadShareData> tl = new ThreadLocal<>();

    public static ThreadShareData getCurrentThreadInstance(){
        ThreadShareData shareData = tl.get();
        if(shareData == null){
            shareData = new ThreadShareData();
            tl.set(shareData);
        }
        return shareData;
    }
}
