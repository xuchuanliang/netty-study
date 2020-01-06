package com.ant.practice4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MyDecoder3 extends MessageToMessageDecoder<Integer> {
    @Override
    protected void decode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        System.out.println("ffff");
        out.add(String.valueOf(msg)+" haha");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().closeFuture().sync();
    }

    @Test
    public void t1(){
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new MyDecoder3());
        ByteBuf byteBuf = Unpooled.buffer();
        for(int i=0;i<10;i++){
            byteBuf.writeInt(i);
        }
        embeddedChannel.writeInbound(byteBuf);
        ByteBuf o = embeddedChannel.readInbound();
        byte[] bytes = new byte[o.readableBytes()];
        o.getBytes(o.readerIndex(),bytes);
        String s = new String(bytes,0,o.readableBytes());
        System.out.println(s);
    }
}
