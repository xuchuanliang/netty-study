package com.ant.practice3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 自定义消息解码器
 */
public class MyByteToMessageDecoder extends ByteToMessageDecoder {

    private final int frameLength;

    public MyByteToMessageDecoder(int frameLength) {
        if(frameLength <= 0){
            throw new IllegalArgumentException();
        }
        this.frameLength = frameLength;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(byteBuf.readableBytes() >= frameLength){
            ByteBuf buf = byteBuf.readBytes(frameLength);
            list.add(buf);
        }
    }
}
