package com.iteller.foo.nio;

import org.junit.Test;

import java.nio.*;

/**
 * Created by 18060903 on 2018/8/15.
 */
public class TestByteBuffer {

    @Test
    public void testAllocate(){
        ByteBuffer bb = ByteBuffer.allocate(16);
        System.out.println(bb.hasArray());
    }

    @Test
    public void testAllocateDirect(){
        ByteBuffer bb = ByteBuffer.allocateDirect(16);
        System.out.println(bb.isDirect());
    }

    @Test
    public void testArray(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        byte[] bytes = byteBuffer.array();
        System.out.println(bytes.length);
    }

    @Test
    public void testArrayOffset(){
        ByteBuffer bb = ByteBuffer.allocate(16);
        System.out.println(bb.arrayOffset());
    }

    @Test
    public void testConvert(){
        ByteBuffer bb = ByteBuffer.allocate(16);
        CharBuffer cb = bb.asCharBuffer();
        DoubleBuffer db = bb.asDoubleBuffer();
        FloatBuffer fb = bb.asFloatBuffer();
        IntBuffer ib = bb.asIntBuffer();
        LongBuffer lb = bb.asLongBuffer();
        ShortBuffer sb = bb.asShortBuffer();
        System.out.println(bb);
        System.out.println(cb);
        System.out.println(db);
        System.out.println(fb);
        System.out.println(ib);
        System.out.println(lb);
        System.out.println(sb);
    }

    @Test
    public void testCompact(){
        ByteBuffer bb = ByteBuffer.allocate(16);
        System.out.println(bb);
        bb.putInt(0, 10);
        System.out.println(bb.compact());
    }

    @Test
    public void testCompare(){
        ByteBuffer bb = ByteBuffer.allocate(16);
        ByteBuffer bb2 = ByteBuffer.allocate(8);
        System.out.println(bb.compareTo(bb2));
    }

    @Test
    public void testDuplicate(){
        ByteBuffer bb = ByteBuffer.allocate(16);
        System.out.println(bb);
        bb.putInt(0, 10);
        System.out.println(bb.getInt());
        ByteBuffer bb2 = bb.duplicate();
        System.out.println(bb2);
        bb2.putLong(8, 20);
        System.out.println(bb2.getLong());
    }

    @Test
    public void testGet(){
        ByteBuffer bb = ByteBuffer.allocate(16);
        System.out.println(bb.get());
        byte[] dst = {1,2,3,4,5};
        bb = bb.get(dst);
        System.out.println(bb.get());
        System.out.println(bb.get(1));
    }

    @Test
    public void testPutIndexLong(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        byteBuffer.putLong(0, 10);
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.get());
        System.out.println(byteBuffer.get());
    }

    @Test
    public void testOrder(){
        ByteBuffer bb = ByteBuffer.allocate(16);
        ByteBuffer nb = bb.put(new byte[]{1,2,4,5,2,3});
        System.out.println(nb.order());
        ByteOrder bo = nb.order();
        System.out.println(bo.toString());
    }

    @Test
    public void test(){
        ByteBuffer bb = ByteBuffer.allocate(16);
        System.out.println("position:" + bb.position());
        System.out.println("limit:" + bb.limit());
        System.out.println("capacity:" + bb.capacity());
    }

    @Test
    public void testIntegration(){
        ByteBuffer bb = ByteBuffer.allocate(16);
        bb.put((byte) 1);
        System.out.println(bb.get());
        System.out.println("position:" + bb.position());
        System.out.println("limit:" + bb.limit());
        System.out.println("capacity:" + bb.capacity());
    }

}
