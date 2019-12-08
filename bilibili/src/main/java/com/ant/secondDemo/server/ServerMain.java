package com.ant.secondDemo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 模拟socket编程
 */
public class ServerMain {
    public static void main(String[] args) {
        //转发线程池
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        //工作线程池
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);
        try{
            //引导类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //设置线程组
            ChannelFuture channelFuture = serverBootstrap.group(bossGroup, workerGroup)
                    //设置管道类型
                    .channel(NioServerSocketChannel.class)
                    //设置线程处理器，注意childHandler是将处理器交由workerGroup执行,如果是ServerBootstrap的handler方法中指定的处理器是教给parentGroup(bossGoup)线程池处理运行
                    .childHandler(new MySocketServerInitializer())
                    //绑定端口
                    .bind(8899)
                    //同步阻塞运行
                    .sync();
            //关闭
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
