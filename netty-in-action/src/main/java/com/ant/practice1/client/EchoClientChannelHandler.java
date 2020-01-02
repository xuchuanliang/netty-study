package com.ant.practice1.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoClientChannelHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //当连接建立完成后向服务端发送消息
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks", CharsetUtil.UTF_8));
    }

    /* 此处存在粘包和拆包问题

        每当接收数据时，都会调用这个方法。需要注
        意的是，由服务器发送的消息可能会被分块接收。 也就是说，如果服务器发送了 5 字节， 那么不
        能保证这 5 字节会被一次性接收。 即使是对于这么少量的数据， channelRead0()方法也可能
        会被调用两次，第一次使用一个持有 3 字节的 ByteBuf（Netty 的字节容器），第二次使用一个
        持有 2 字节的 ByteBuf。作为一个面向流的协议， TCP 保证了字节数组将会按照服务器发送它
        们的顺序被接收。
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
