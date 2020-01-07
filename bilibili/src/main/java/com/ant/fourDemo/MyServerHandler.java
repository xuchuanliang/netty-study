package com.ant.fourDemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<PersonInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonInfo.MyMessage msg) throws Exception {
        System.out.println("收到客户端的消息");
        ProtoDynamicTypeUitl.printProtoDynamicType(msg);
        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().closeFuture().sync();
    }
}
