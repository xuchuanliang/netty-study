package com.ant.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test2 {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("D:/code/code_idea/netty-study/bilibili/hello.txt");
        //转换成Channel
        FileChannel channel = fileInputStream.getChannel();
        //创建一个字节Buffer,缓冲区大小是0.5M
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        //使用channel向byteBuffer中写数据，也就是将硬盘中的文件读出来写到Buffer中
        channel.read(byteBuffer);
        //现在byteBuffer还是写的状态
        //从ByteBuffer中读出数据到内存中，那么需要将ButeBuffer从写的状态切换为读的状态
        byteBuffer.flip();
        //开始从ByteBuffer中读数据到内存
        while (byteBuffer.hasRemaining()){
            System.out.println((char)byteBuffer.get());
        }
    }
}
