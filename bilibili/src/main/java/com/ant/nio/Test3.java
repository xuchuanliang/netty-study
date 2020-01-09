package com.ant.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class Test3 {
    public static void main(String[] args) throws Exception{
        FileOutputStream fileOutputStream = new FileOutputStream("hello.txt");
        //1.构造管道Channel
        FileChannel channel = fileOutputStream.getChannel();
        //2.开辟内存中的byteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        //3.将需要写入的信息写入到内存缓冲区中（写入到byteBuffer中）
        byte[] bytes = "HELLO,XUCHUANLIANG,SO COOL,徐传良".getBytes(Charset.forName("gbk"));
        for(int i=0;i<bytes.length;i++){
            byteBuffer.put(bytes[i]);
        }
        //4.channel从内存缓冲区中读取数据并写入到硬盘中，则将byteBuffer当前的写状态切换为读状态
        byteBuffer.flip();
        //5.channel将byteBuffer中的数据读出来并且写入到内存中
        channel.write(byteBuffer);
        fileOutputStream.close();

    }
}
