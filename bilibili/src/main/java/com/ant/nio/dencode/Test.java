package com.ant.nio.dencode;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * 编解码问题以及字符集问题
 */
public class Test {
    public static void main(String[] args) throws Exception{
        RandomAccessFile in = new RandomAccessFile("file_in.txt","rw");
        RandomAccessFile out = new RandomAccessFile("file_out.txt","rw");
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();
        MappedByteBuffer byteBuffer = inChannel.map(FileChannel.MapMode.READ_WRITE,0,new File("file_in.txt").length());
        int read = inChannel.read(byteBuffer);
        Charset charset = Charset.forName("iso-8859-1");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();
        byteBuffer.flip();
        CharBuffer charBuffer = decoder.decode(byteBuffer);
        ByteBuffer byteBuffer1 = encoder.encode(charBuffer);
        outChannel.write(byteBuffer1);
        inChannel.close();
        outChannel.close();
    }
}
