package com.ant.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Test9 {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8899));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            int i = selector.select();
            SocketChannel accept = serverSocketChannel.accept();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("xuchuanliang".getBytes());
            buffer.flip();
            accept.read(buffer);
            System.out.println("结束");
        }
    }
}
