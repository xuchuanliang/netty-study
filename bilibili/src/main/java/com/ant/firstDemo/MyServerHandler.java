package com.ant.firstDemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.lang.reflect.Method;
import java.net.URI;

import static com.ant.PrintUtil.println;

public class MyServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if(msg instanceof HttpRequest){
            HttpRequest request = (HttpRequest) msg;
            URI uri = new URI(request.uri());
            System.err.println(uri.toString());
            if("/favicon.ico".equalsIgnoreCase(uri.toString())){
                System.err.println("请求图标");
                return;
            }
            System.err.println("进入自定义处理器的channelRead0方法");
            ByteBuf byteBuf = Unpooled.copiedBuffer("hello client", CharsetUtil.UTF_8);
            FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,byteBuf);
            httpResponse.headers().add(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            httpResponse.headers().add(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
            ctx.writeAndFlush(httpResponse);
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        println("channel registered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        println("channel unRegistered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        println("channel active");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        println("channel inActive");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        println("channel read");
        super.channelRead(ctx,msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        println("read complete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        println("exception caught");
        ctx.pipeline().close();
    }
}
