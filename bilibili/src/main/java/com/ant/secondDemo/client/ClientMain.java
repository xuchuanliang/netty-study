package com.ant.secondDemo.client;

import com.ant.PrintUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * socket client端
 */
public class ClientMain {
    public static void main(String[] args) {
        EventLoopGroup eventExecutors = new NioEventLoopGroup(2);
        try{
            Bootstrap bootstrap = new Bootstrap();
            ChannelFuture channelFuture = bootstrap
                    .group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new MySockerClientInitializer())
                    .connect("127.0.0.1", 8899)
                    .sync();
            channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if(future.isSuccess()){
                        System.out.println("服务器连接完成");
                    }
                }
            });
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
