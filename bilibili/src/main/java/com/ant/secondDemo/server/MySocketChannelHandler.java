package com.ant.secondDemo.server;

import com.ant.PrintUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

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
        ByteBuf byteBuf = Unpooled.buffer("hello i am server".length());
        byteBuf.writeBytes("hello i am server".getBytes());
        String content = "hello i am server";
        ByteBuf byteBuf1 = Unpooled.buffer(content.getBytes().length);
        byteBuf1.writeBytes(byteBuf1);
        ctx.channel().writeAndFlush(byteBuf1);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        try{
            PrintUtil.println(ctx.channel().remoteAddress()+":"+msg);
            ctx.channel().writeAndFlush("from server " + UUID.randomUUID());
        }finally {
            //释放缓存
            //从InBound里读取的ByteBuf要手动释放，还有自己创建的ByteBuf要自己负责释放。这两处要调用这个release方法。
            //write Bytebuf到OutBound时由netty负责释放，不需要手动调用release
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
