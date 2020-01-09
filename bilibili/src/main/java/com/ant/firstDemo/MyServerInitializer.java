package com.ant.firstDemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

import static com.ant.PU.println;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        println("初始化initializer");
        ChannelPipeline pipeline = ch.pipeline();
        //Netty提供的用于处理HHTP请求编辑码用的
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        pipeline.addLast("myServerHandler",new MyServerHandler());
    }
}
