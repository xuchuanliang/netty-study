package com.ant.nio.chat;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用java原生NIO的服务端
 * 深入理解Netty-张龙：43课对NIO讲解的非常透彻
 */
public class Server {

    private static final Map<String, SocketChannel> MAP = new ConcurrentHashMap<>(256);

    public static void main(String[] args) throws Exception {
        //1.创建ServerSocketChannel，绑定端口
        //1.1 创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //1.2 设置Channel模式是非阻塞
        serverSocketChannel.configureBlocking(false);
        //1.2 获取该ServerSocketChannel的ServerSocket对象
        ServerSocket serverSocket = serverSocketChannel.socket();
        //1.3绑定端口
        serverSocket.bind(new InetSocketAddress(8899));
        //2.创建selector
        Selector selector = Selector.open();
        //3.将ServerSocketChannel注册到Selector中，因为此时刚注册，所以监听连接事件，因为是客户端向服务器端发起连接，所以服务器端只需要监听连接请求的事件即可，客户端需要监听发起连接的事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //4.开始进入死循环等待连接
        while (true) {
            //5.阻塞直到有监听的事件进入，即有连接进来（查看该方法源码可知该方法是一个阻塞方法，会阻塞直到有监听的事件进入）
            selector.select();
            //6.获取监听事件的selectKey集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //7.对每一个监听的事件进行遍历处理
            selectionKeys.forEach(selectionKey -> {
                SocketChannel socketChannel;
                try {
                    if (selectionKey.isAcceptable()) {
                        //1.如果是客户端请求建立连接
                        //2因为监听接收事件的channel都是ServerSocketChannel，所以此处强转,【注意：ServerSocketChannel相当于是服务端接受连接的大门，理解成只负责接受连接，一旦接受完连接后则后面的处理都是SocketChannel来处理】
                        ServerSocketChannel s = (ServerSocketChannel) selectionKey.channel();
                        //3.接受连接
                        socketChannel = s.accept();
                        //4.设置当前通道模式为非阻塞
                        socketChannel.configureBlocking(false);
                        //4.将当前与客户端建立的管道SocketChannel注册到Selector中，并且监听有消息可读
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        //将连接保存到集合中，供后面发消息使用
                        MAP.put("[" + UUID.randomUUID().toString() + "]", socketChannel);
                    } else if (selectionKey.isReadable()) {
                        //如果客户端发送数据,则标记当前客户端，且发送到其他的所有客户端
                        //1.获取SocketChannel通道，此处之所以能够转成SocketChannel，因为我建监听read事件的Channel只有SocketChannel这一个类型
                        socketChannel = (SocketChannel) selectionKey.channel();
                        //2.获取当前sockectChannel的名称
                        String currSockeChannelName = "";
                        Set<Map.Entry<String, SocketChannel>> entries = MAP.entrySet();
                        for (Map.Entry<String, SocketChannel> e : entries) {
                            if (e.getValue().equals(socketChannel)) {
                                currSockeChannelName = e.getKey();
                                break;
                            }
                        }
                        //读取消息
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        while (true) {
                            //1.因为是循环，先清空
                            byteBuffer.clear();
                            //2.开始读取消息
                            int readInt = socketChannel.read(byteBuffer);

                            //3.拼装消息
                            byteBuffer.put(("from " + currSockeChannelName).getBytes());
                            //4.因为下面开始向其他客户端写，所以反转
                            byteBuffer.flip();
                            //5因为要循环向多个客户端写，所以标记一下初始位置，每一次写直接reset从头开始写即可
                            byteBuffer.mark();
                            //4.开始向所有的客户端写消息
                            ByteBuffer ent = ByteBuffer.wrap("\r\n".getBytes());
                            for (Map.Entry<String, SocketChannel> e : entries) {
                                byteBuffer.reset();
                                e.getValue().write(byteBuffer);
                                e.getValue().write(ent);
                            }
                            if (readInt <= 0) {
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            //切忌处理完需要将当前selectKey清空
            selectionKeys.clear();
        }
    }
}
