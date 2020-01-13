package com.ant.nio;

import com.ant.threeDemo.TextWebsocketFrameChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.serialization.CompatibleObjectEncoder;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Test8 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("hello.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = MappedByteBuffer.allocate(512);

    }

    public static void test() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            ServerBootstrap serverBootstrap1 = serverBootstrap
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {

                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline()
                                    //http请求响应编解码
                                    .addLast(new HttpRequestDecoder())
                                    .addLast(new HttpRequestEncoder())
                                    .addLast(new HttpResponseDecoder())
                                    .addLast(new HttpResponseEncoder())
                                    //http content请求响应压缩
                                    .addLast(new HttpContentDecompressor())
                                    .addLast(new HttpContentCompressor())
                                    .addLast(new SslHandler(null))
                                    .addLast(new HttpObjectAggregator(65536))
                                    .addLast(new WebSocketServerProtocolHandler("ws"))
                                    .addLast(new TextWebsocketFrameChannelHandler())
                                    //空闲连接管理
                                    .addLast(new IdleStateHandler(10, 20, 30))
                                    .addLast(new ReadTimeoutHandler(100))
                                    .addLast(new WriteTimeoutHandler(100))
                                    //基于换行符分隔
                                    .addLast(new LineBasedFrameDecoder(1024))
                                    .addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("haha".getBytes())))
                                    //基于长度的协议解析：基于已知定长或自定义
                                    .addLast(new FixedLengthFrameDecoder(32))
                                    .addLast(new LengthFieldBasedFrameDecoder(10, 10, 10))
                                    //基于jdk序列化
                                    .addLast(new CompatibleObjectEncoder())
                                    .addLast(new ObjectEncoder())
                                    .addLast(new ObjectDecoder(null))
                                    //基于marshalling进行序列化
                                    .addLast(new MarshallingDecoder(null))
                                    .addLast(new MarshallingEncoder(null))
                                    //基于protobuf的方式进行序列化
                                    .addLast(new ProtobufEncoder())
                                    .addLast(new ProtobufDecoder(null))
                                    .addLast(new ProtobufVarint32FrameDecoder())
                                    .addLast(new ProtobufVarint32LengthFieldPrepender())

                            ;
                        }
                    });
        } catch (Exception e) {

        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}

class MyChannelHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    /**
     * 空闲时会调用该服务
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            ctx.close().sync();
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
