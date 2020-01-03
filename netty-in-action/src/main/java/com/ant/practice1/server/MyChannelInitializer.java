package com.ant.practice1.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel
                .pipeline()
                .addLast(new EchoServerChannelHandler())
                .addLast(new EchoServerChannelHandler2());
    }
}
