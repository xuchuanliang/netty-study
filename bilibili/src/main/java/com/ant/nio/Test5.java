package com.ant.nio;

import com.ant.PU;

import java.nio.ByteBuffer;

/**
 * 类型化的get和put
 */
public class Test5 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        byteBuffer.putChar('徐');
        byteBuffer.putDouble(123.123456);
        byteBuffer.putFloat((float) 1.2);
        byteBuffer.putShort((short) 1);

        byteBuffer.flip();

        PU.println(byteBuffer.getChar());
        PU.println(byteBuffer.getDouble());
        PU.println(byteBuffer.getFloat());
        PU.println(byteBuffer.getShort());
    }
}
