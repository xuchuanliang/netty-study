//package com.ant.practice2.server;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.ServerSocketChannel;
//import io.netty.channel.socket.SocketChannel;
//
//public class ServerMain {
//    public static void main(String[] args) {
//        EventLoopGroup boss = new NioEventLoopGroup();
//        EventLoopGroup worker = new NioEventLoopGroup();
//        ServerBootstrap serverBootstrap = new ServerBootstrap();
//        serverBootstrap.group(boss,worker).channel(ServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
//            protected void initChannel(SocketChannel socketChannel) throws Exception {
//                socketChannel.pipeline().addLast(new SimpleChannelInboundHandler<ByteBuf>() {
//                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
//
//                    }
//                });
//            }
//        }).bind(9999).sync();
//    }
//}
