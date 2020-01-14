package com.ant.nio.chat;

import com.ant.PU;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * java原生NIO实现客户端
 */
public class Client {
    public static void main(String[] args) throws Exception{
        //1.创建Channel
        SocketChannel socketChannel = SocketChannel.open();
        //2.设置为非阻塞
        socketChannel.configureBlocking(false);
        //3.设置连接地址
        socketChannel.connect(new InetSocketAddress("10.100.33.32",8899));
        //4.创建选择器selector
        Selector selector = Selector.open();
        //5.将Channel注册到选择器中，监听连接事件，因为客户端是主动向服务端连接，所以监听连接事件而不是监听接收连接事件
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        //6.死循环获取到监听事件发生
        while (true){
            //阻塞直到有监听到的事件发生
            selector.select();
            //获取触发监听的时间SelectionKey集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                //监听连接事件，如果连接上了，则强转成SocketChannel，至于为什么能够强转，可以参见{@link Server2}
                if(selectionKey.isConnectable()){
                    SocketChannel s = (SocketChannel) selectionKey.channel();
                    //如果允许建立连接，则手动完成建立连接，具体可看源码注释
                    if(s.isConnectionPending()){
                        s.finishConnect();
                        //向服务器端写出数据
                        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                        writeBuffer.put((LocalTime.now() + "创建连接成功").getBytes());
                        //由向Buffer中写数据到从Buffer中向外读数据，切忌要flip一下进行翻转
                        writeBuffer.flip();
                        s.write(writeBuffer);
                        //从键盘输入文件写出到服务器端
                        ExecutorService executorService = Executors.newSingleThreadExecutor();
                        executorService.submit(()->{
                            try{
                                PU.println("请输入数据：");
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                                String writeStr = bufferedReader.readLine();
                                //清除buffer
                                writeBuffer.clear();
                                //向buffer中写数据
                                writeBuffer.put(writeStr.getBytes());
                                //翻转buffer
                                writeBuffer.flip();
                                //从buffer中向服务器端写数据
                                s.write(writeBuffer);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        });
                        //连接成功后，注册读事件，能够读服务器端读取数据
                        s.register(selector,SelectionKey.OP_READ);
                    }
                }else if(selectionKey.isReadable()){
                    //开始读事件
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    SocketChannel s = (SocketChannel) selectionKey.channel();
                    int count = s.read(byteBuffer);
                    if(count > 0){
                        String readStr = new String(byteBuffer.array());
                        System.out.print("读取到数据："+readStr);
                    }
                }
                iterator.remove();
            }
        }
    }
}
