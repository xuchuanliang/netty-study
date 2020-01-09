package com.ant.nio;

import java.nio.ByteBuffer;

/**
 * 只读buffer，普通buffer可随时转成只读buffer，但是只读buffer无法转成普通buffer
 */
public class Test7 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        for(int i=0;i<byteBuffer.capacity();i++){
            byteBuffer.put((byte)i);
        }
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
    }
}
