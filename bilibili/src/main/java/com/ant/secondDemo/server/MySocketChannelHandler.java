package com.ant.secondDemo.server;

import com.ant.PrintUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

/**
 * 自定义处理类
 */
public class MySocketChannelHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 建立连接时，向客户端发送欢迎消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        PrintUtil.println("客户端连接上");
        PrintUtil.println(ctx.channel().remoteAddress()+":");
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello i am server", CharsetUtil.UTF_8);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        PrintUtil.println(ctx.channel().remoteAddress()+":"+msg);
        ctx.channel().writeAndFlush("from server " + UUID.randomUUID());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
