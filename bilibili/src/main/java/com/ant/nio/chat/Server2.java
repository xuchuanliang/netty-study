package com.ant.nio.chat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用java 原生NIO实现聊天程序的服务端
 * 1.关于ServerSocketChannel和SocketChannel的区别：ServerSocketChannel相当于是服务端接受请求的大门，
 * 与客户端建立连接后，后序的数据读写操作都由SocketChannel处理，相当于ServerSocketChannel只负责建立连接
 */
public class Server2 {
    private static final Map<String, SocketChannel> MAP = new ConcurrentHashMap<>(16);

    public static void main(String[] args) throws Exception {
        //1.创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2.设置Channel的模式是非阻塞
        serverSocketChannel.configureBlocking(false);
        //3.获取ServerSocket，绑定监听端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8899));
        //4.创建Selector
        Selector selector = Selector.open();
        //5.将ServerSocketChannel绑定到Selector，并且由于目前还没建立连接，则建立客户端的连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6.开始死循环，监听请求并处理
        while (true) {
            //7.阻塞当前线程直到有监听的事件触发
            selector.select();
            //8.获取监听到的事件SelectionKey
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //9.对监听到的事件进行处理
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SocketChannel socketChannel;
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    //1.如果是建立连接请求，则强转成ServerSocketChannel，注意：此处之所以能够转成ServerSocketChannel，
                    // 是因为我们监听建立连接请求的Channel只有ServerSocketChannel，所以获取到的Channel是ServerSocketChannel
                    ServerSocketChannel s = (ServerSocketChannel) selectionKey.channel();
                    //2.接受客户端建立连接的请求并获取建立连接的SocketChannel
                    socketChannel = s.accept();
                    //3.将建立完连接的Channel的阻塞模式设置成非阻塞
                    socketChannel.configureBlocking(false);
                    //4.将建立完连接的Channel注册到Selector中，并且监听有数据可读的事件
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    //5.移除当前ServerSocketChannel,否则会报空指针
                    iterator.remove();
                    //6.将当前通道（Channel）保存
                    MAP.put("[" + UUID.randomUUID().toString() + "]", socketChannel);
                } else if (selectionKey.isReadable()) {
                    //1.如果监听到的是可读事件，那么强转成SocketChannel，原因同上
                    socketChannel = (SocketChannel) selectionKey.channel();
                    //2.开辟一个ByteBuffer，并且开始不断读数据
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int readInt;
                    //因为是死循环，所以先清空ByteBuffer，以便进行新的读操作
                    readInt = socketChannel.read(byteBuffer);
                    if (readInt <= 0) {
                        //未读取到数据，break
                        break;
                    }
                    byteBuffer.flip();
                    //3.读到的字节转成字符串
                    Charset charset = Charset.forName("utf-8");
                    String readStr = String.valueOf(charset.decode(byteBuffer).array());
                    System.out.println("读取到数据：" + readStr);
                    //4.开始向所有的客户端写数据
                    //4.1获取当前客户端name
                    String currentChannelName = "";
                    for (Map.Entry<String, SocketChannel> e : MAP.entrySet()) {
                        if (e.getValue() == socketChannel) {
                            currentChannelName = e.getKey();
                            break;
                        }
                    }
                    //4.2将需要写出的数据进行拼接
                    String writeStr = currentChannelName + ":" + readStr;
                    //4.3 向每一个客户端写数据
                    for (Map.Entry<String, SocketChannel> e : MAP.entrySet()) {
                        ByteBuffer b = ByteBuffer.allocate(1024);
                        b.put(writeStr.getBytes(charset));
                        b.flip();
                        e.getValue().write(b);
                    }

                    //重要，将当前socketChannel移除
                    iterator.remove();
                }
            }
        }
    }
}
