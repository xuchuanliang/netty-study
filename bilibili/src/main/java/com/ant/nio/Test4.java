package com.ant.nio;

import com.ant.PU;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test4 {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("in.txt");
        FileOutputStream fileOutputStream  = new FileOutputStream("out.txt");
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        while (true){
            byteBuffer.clear();//important
            int read = inputStreamChannel.read(byteBuffer);
            if(read == -1){
                break;
            }
            PU.println(read+"");
            byteBuffer.flip();
            outputStreamChannel.write(byteBuffer);
        }
    }
}
