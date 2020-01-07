package com.ant.fourDemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class MyClientChannelHandler extends SimpleChannelInboundHandler<PersonInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonInfo.MyMessage msg) throws Exception {
        System.out.println("收到服务器端的消息");
        ProtoDynamicTypeUitl.printProtoDynamicType(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int i = new Random().nextInt(2);
        PersonInfo.MyMessage myMessage = ProtoDynamicTypeUitl.build(i);
        ctx.writeAndFlush(myMessage);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().closeFuture().sync();
    }
}
