package com.ant.nio;

import com.ant.PU;

import java.nio.ByteBuffer;

/**
 * slice：分片/切片，相当于原来buffer的快照，修改之后会在原buffer体现
 */
public class Test6 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for(int i=0;i<byteBuffer.capacity();i++){
            byteBuffer.put((byte)i);
        }
        byteBuffer.position(2);
        byteBuffer.limit(6);
        ByteBuffer newBuffer = byteBuffer.slice();
        for(int i=0;i<newBuffer.capacity();i++){
            byte b = newBuffer.get(i);
            newBuffer.put(i,(byte) (b*2));
        }
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());
        for(int i=0;i<byteBuffer.limit();i++){
            PU.println(byteBuffer.get());
        }
    }
}
