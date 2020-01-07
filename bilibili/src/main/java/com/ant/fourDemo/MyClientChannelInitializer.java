package com.ant.fourDemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class MyClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new ProtobufVarint32FrameDecoder())//在对protobuf类进行解码时去除字节流前面的长度标记
                .addLast(new ProtobufDecoder(PersonInfo.MyMessage.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())//在对protobuf类进行编码时在字节流前面增加长度标记
                .addLast(new ProtobufEncoder())
                .addLast(new MyClientChannelHandler());
    }
}
