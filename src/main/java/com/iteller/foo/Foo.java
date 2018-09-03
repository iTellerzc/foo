package com.iteller.foo;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Created by 18060903 on 2018/6/28.
 */
public class Foo {
    public static void main(String[] args) throws IOException {
        /*Process process = Runtime.getRuntime().exec("java -version");
        OutputStreamWriter osr = new OutputStreamWriter(process.getOutputStream());*/
        //System.out.println(UUID.randomUUID().toString());
        int b =16;
        System.out.println((byte) b);
        System.out.println(Integer.bitCount(12));

    }
}
