package com.ant.fourDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class ServerMain {
    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            ChannelFuture future = bootstrap
                    .channel(NioServerSocketChannel.class)
                    .group(boss, worker)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new MyServerChannelInitializer())
                    .bind(8899)
                    .sync();
            future.channel().closeFuture().sync();
        }catch (Exception e){

        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
