package com.ant.firstDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 模拟http请求响应
 */
public class ServerMain {
    public static void main(String[] args){
        //转发线程池
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(2);
        //工作线程池
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup(4);
        try{
            //启动服务端引导类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            ChannelFuture channelFuture = serverBootstrap
                    //线程组
                    .group(bossLoopGroup, workerLoopGroup)
                    //管道
                    .channel(NioServerSocketChannel.class)
                    //childHandler中的处理器是使用workder中的线程处理
                    //处理器
                    .childHandler(new MyServerInitializer())
                    //绑定端口
                    .bind(8899)
                    //同步
                    .sync();
            //同步关闭
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //优雅关闭转发线程池和工作线程池
            bossLoopGroup.shutdownGracefully();
            workerLoopGroup.shutdownGracefully();
        }
    }
}
