package com.ant.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        Random random = new SecureRandom();
        for(int i=0;i<10;i++){
            intBuffer.put(random.nextInt(100));
        }
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
