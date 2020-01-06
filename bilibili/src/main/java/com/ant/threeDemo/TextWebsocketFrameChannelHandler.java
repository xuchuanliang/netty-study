package com.ant.threeDemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalTime;

/**
 * websockt
 */
public class TextWebsocketFrameChannelHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("接收到websocket 客户端的消息："+msg.text());
//        ctx.writeAndFlush()
//        ctx.channel().writeAndFlush("我是服务器端");
        ctx.channel().writeAndFlush(new TextWebSocketFrame("我是服务端，现在时间是；"+ LocalTime.now()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler add");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler removed");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("caught exception");
        ctx.channel().closeFuture().sync();
    }
}
