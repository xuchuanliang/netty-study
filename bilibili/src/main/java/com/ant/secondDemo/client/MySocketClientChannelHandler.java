package com.ant.secondDemo.client;

import com.ant.PrintUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MySocketClientChannelHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        PrintUtil.println("i got message from server :"+msg);
        ctx.writeAndFlush("HELLO,i am client,i got message from server :"+msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.writeAndFlush("HELLO,i am client,i got message from server :"+msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        PrintUtil.println("active");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
