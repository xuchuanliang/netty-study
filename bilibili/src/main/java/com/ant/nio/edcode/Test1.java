package com.ant.nio.edcode;

import com.ant.PU;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

/**
 * 编解码
 */
public class Test1 {
    public static void main(String[] args) throws Exception{
        RandomAccessFile in = new RandomAccessFile("test_in.txt","rw");
        RandomAccessFile out = new RandomAccessFile("test_out.txt","rw");
        Charset charset = Charset.forName("utf-8");
        CharsetEncoder encoder = charset.newEncoder();
        CharsetDecoder decoder = charset.newDecoder();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();
        int read = inChannel.read(byteBuffer);
        byteBuffer.flip();
        CharBuffer decode = decoder.decode(byteBuffer);
        ByteBuffer buffer = encoder.encode(decode);
        outChannel.write(buffer);
        inChannel.close();
        outChannel.close();
        SortedMap<String, Charset> sortedMap = Charset.availableCharsets();
        sortedMap.forEach((k,v)->{
            PU.println(k + ":" + v);
        });
    }
}
