package com.ant.threeDemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSockeChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());//http请求编解码器
        pipeline.addLast(new ChunkedWriteHandler());//组合处理器，主要作用处理大块请求数据
        pipeline.addLast(new HttpObjectAggregator(8192));//聚合http请求
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
    }
}
