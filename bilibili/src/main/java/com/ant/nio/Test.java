package com.ant.nio;

import com.ant.PrintUtil;

import java.nio.IntBuffer;
import java.security.SecureRandom;
import java.util.Random;

import static com.ant.PrintUtil.*;

public class Test {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        Random random = new SecureRandom();
        println("1 position:"+intBuffer.position());
        println("1 limit:"+intBuffer.limit());
        println("1 capacity:"+intBuffer.capacity());
        println("");
        for(int i=0;i<5;i++){
            intBuffer.put(random.nextInt(100));
        }
        println("2 position:"+intBuffer.position());
        println("2 limit:"+intBuffer.limit());
        println("2 capacity:"+intBuffer.capacity());
        println("");
        intBuffer.flip();
        println("3 position:"+intBuffer.position());
        println("3 limit:"+intBuffer.limit());
        println("3 capacity:"+intBuffer.capacity());
        println("");
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
            println("- position:"+intBuffer.position());
            println("- limit:"+intBuffer.limit());
            println("- capacity:"+intBuffer.capacity());
        }
    }
}
