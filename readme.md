# netty 学习 -- 哔哩哔哩 张龙老师
- java中BIO是使用装饰者设计模式，是面向流编程，主要包含InputStream和OutputStream两个概念

- java中NIO是面向块或buffer编程，主要包含Selector、Channel和Buffer三个概念（选择器、管道、缓冲区）；Buffer本身是一块内存，
底层实现上是一个数组，数据的读、写都是通过Buffer来实现的。具体关系可以参见毕加索·龙画的图，在file中，对应关系就是Thread(1)-->Selector(1)-->Channel(N)-->Buffer(N)；
其中与BIO的区别是BIO从流（stream）中读取数据，读到一个字节就就是一个字节，而NIO是channel将数据读取到Buffer中，程序从buffer再获取数据，
程序写数据也是将数据写到Buffer中，然后channel再将数据从buffer写到其他地方；所以BIO中输入和输出流是不兼容的，
但是NIO中输入和输出都是通过Buffer缓冲区来实现，Buffer同时承担这读和写的工作，所以需要通过调用Buffer的flip()来切换读和写的状态。

- Buffer提供了对于数据的结构化访问，并且可以追踪到系统的读写过程。

- java中8种基本数据类型都包含对应的Buffer类型，如IntBuffer、DoubleBuffer等

- Channel指的是可以向其写入或者从中读取数据的对象，它类似于java.io中的Stream

- 所有数据的读写都是通过Buffer来进行的，永远不会出现直接向Channel写入数据的情况，或者直接从Channel中读取数据的情况。

- 与Stream不同的是，Channel是双向的，但是流只可能是InputStream或者OutputStream，Channel打开后则可以进行读取、写入或者读写

- 由于Channel是双向的，因此它能更好的反映出低层操作系统的真实情况；在Linux系统中，底层操作系统的通道就是双向的。

- **注意通过Channel读和写都是针对Buffer而言的，向Buffer中写数据或者从Buffer中读数据**

> 对NIO和BIO不同地方的个人理解（以从硬盘中读取数据为例）：
>BIO中相当于我们程序通过InputStream和OutputStream直接与硬盘建立通道进行交互，从硬盘中直接读取数据或者写入数据；
>byteBuffer实际上指的是一块内存空间，NIO中程序直接与ByteBuffer进行交互，向byteBuffer中进行读写数据，Channel相当于ByteBuffer与硬盘之间的管道，
>Channel负责将byteBuffer中的数据写入硬盘或将硬盘中的数据读取到byteBuffer中，因此每一次一次的IO操作涉及到首先程序向ByteBuffer中写数据，
>然后Channel从ByteBuffer中读数据写到硬盘，所以每一次都需要调用byteBuffer.filp()方法来切换byteBuffer的读写状态
>总而言之，NIO相当于是把原来的BIO的一步操作切断成两步操作，并且数据通过中间的一个数据缓冲区（ByteBuffer，即从内存中开辟的一个数组空间）来进行中转。详情可看本项目file中的：NIO和BIO的个人理解本质区别.png

- Nio中的Buffer的操作方法分为两种，一种是绝对方法，一种是相对方法，源码注释中分别是Relative和Absolute，相对方法会移动position，绝对方法不会移动position

- Buffer中三个重要的属性：capacity是总容量，不可变的；limit是第一个不能被读或者写的数据的索引；position是下一个应该被读或者写的索引
>通过查看源码我们了解一下IntBuffer的一些细节：
>> 1.IntBuffer.allocate(10)，构建IntBuffer中我们可以看到实际上创建的是IntHeapBuffer()对象，IntHeapBuffer是IntBuffer的一个子类，是在堆内存中开辟一个数组空间，Buffer的数据是存放在这个数组中的。
>> 在NIO中与HeapBuffer相对应的是DirectBuffer，直接内存跟NIO的零拷贝关系比较紧密。
>> 2.intBuffer.put()操作实际上就是将数据放置到int数组中，且将position向后移一位
>> 3.intBuffer.flip()方法就是重置intBuffer，实际上是将limit的位置指向现在position位置，并把position的位置置为0，这样就可以在由读转到写或反之时，能够从0位置开始读或写直到原position(现在limit)的位置
>> 4.intBuffer.clear()将intBuffer置空，看源码实际上是将position置为0，将limit置为与capacity位置相同，mark置为-1（即置为无效）

- 在java传统的堆内存模型下，java程序通过IO写或读数据实际上在java堆外内存，操作系统会开辟一个空间，那么IO实际上是数据是从JVM管理的堆内存拷贝到操作系统开辟的新的内存中，
然后操作系统将该数据写出至IO设备，反过来读数据也是一样，先读取到操作系统开辟的内存空间中，然后再由JVM拷贝到java堆中，所以这里多了一次拷贝消耗。
- 使用NIO的DirectBuffer，就是直接在堆外内存开辟了一个内存空间，java中的DirectBuffer持有该内存空间的地址，当然DirectBuffer对象还是在堆中，
只不过该对象持有堆外开辟的内存地址，这样就避免了多次内存拷贝，实现了零拷贝。
- 疑问：操作系统实际上是能够直接访问到JVM管理的内存空间，为什么使用HeapByteBuffer时操作系统不直接访问来操作，不就不用多拷贝了吗？
但是这样做是有原因的，如果这样操作必须是使用JNI的方式来操作，但是JNI的方式有必须要求内存地址是确定的，但是JVM垃圾收集时会通过标记清除算法进行清除，
那么对象的内存地址会移动，那么就无法进行GC操作，这样肯定也是有问题的。
- 在内存拷贝的时候GC会保证不发生垃圾回收，因为拷贝相对于IO动作很快，这一块由GC保证。
- 零拷贝是指计算机操作的过程中，CPU不需要为数据在内存之间的拷贝消耗资源。而它通常是指计算机在网络发送文件时，不需要将文件内容拷贝到
用户控件而直接在内核空间中传输到网络的方式。此处的内核空间实际上是JVM管理的内存，不需要将文件拷贝到JVM管理的内存中而是能够直接从内核空间进行传输。
```java
package com.ant.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test2 {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("D:/code/code_idea/netty-study/bilibili/hello.txt");
        //转换成Channel
        FileChannel channel = fileInputStream.getChannel();
        //创建一个字节Buffer,缓冲区大小是0.5M
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        //使用channel向byteBuffer中写数据，也就是将硬盘中的文件读出来写到Buffer中
        channel.read(byteBuffer);
        //现在byteBuffer还是写的状态
        //从ByteBuffer中读出数据到内存中，那么需要将ButeBuffer从写的状态切换为读的状态
        byteBuffer.flip();
        //开始从ByteBuffer中读数据到内存
        while (byteBuffer.hasRemaining()){
            System.out.println((char)byteBuffer.get());
        }
    }
}

```

- 编解码问题，从上到下一个字符表示所占用的空间越来越大，直到unicode一个字符使用两个字节表示，不利于存储
>以下编码方式：
>ASCII(美国信息交换标准代码)：7 bit来表示一个字符，即2的7次方，共能表示128个字符（美国能用，但是德国之类国家的字符表示不全）【注意，有可能存在丢失字节问题】
>ISO-8859-1：8 bit表示一个字符，即用一个字节来表示一个字符，共计可表示256个字符，向下完全兼容ASCII编码格式（美国、德国都能用，但是韩国、中国之类国家的字符表示不全）
>GB2312：GB表示国标，用两个字节表示一个汉字，只能表示一部分（中国能用，能够表示一部分汉字）
>GBK：是国标（GB2312）的一种扩展，向下兼容GB2312（中国能用，能够表示跟多的汉字，包含生僻字）
>GB18030：是最完整的国标编码格式（中国能用，能表示所有的中国文字）
>big5：是台湾发明的，能够包含繁体中文的一种编码形式
>unicode：发现哥哥地方定的标准太多了，吃不消了，国标码出来了，能够表示世界上所有国家的所有字符。采用两个字节来表示一个字符；\uxxxxxx
>以下是一种存储格式：
>utf：unicode translation format，unicode是一种编码方式，utf是一种存储方式，utf-8是unicode的实现方式之一。
utf-16LE(little endian),utf-16BE(big endian)：
>utf-8：变长字节表示形式，一般utf8表示英文就使用ASCII编码来表示，中文一般使用三个字节来表示，最多能够使用六个字节来表示一个字符
BOM（byte order mark）:


# Netty in action
- 当实现ChannelInboundHandler或ChannelInboundHandlerAdapter的channelRead()方法时，需要自行负责释放或池化ByteBuf实例相关内存；
  但是实现SimpleChannelInboundHandler的read0()不需要手动释放，因为会自动释放资源
- netty内部使用引用计数方式来进行回收，提供工具类ReferenceCountUtil.release(Object msg)来进行及时释放操作
> 总之，如果一个消息被消费或者丢弃了， 并且没有传递给 ChannelPipeline 中的下一个
 ChannelOutboundHandler
 ， 那么用户就有责任调用 ReferenceCountUtil.release()来释放消息。
 如果消息到达了实际的传输层， 那么当它被写入时或者 Channel 关闭时，都将被自动释放。
- EventLoopGroup相当于一个线程组，EventLoop理解成是一个线程，实际上看类继承关系继承与Executor，是一个执行器。
- ChannelHandlerContext代表了ChannelHandler和ChannelPipeline之间的关系，每当有ChannelHandler添加至ChannelPipeline之中时，
  就会创建ChannelHandlerContext。ChannelHandlerContext的主要作用是管理它所关联的ChannelHandler和在同一个ChannelPipeline中的
  其他ChannelHandler之间的交互。
- Channel或ChannelPipeline调用方法会沿着ChannelPipeLine进行传播。而调用ChannelHandlerContext上相同的方法，则将会从当前关联的
  ChannelHandler开始，并且只会传播给位于该ChannelPipeline中的下一个能够处理该事件的ChannelHandler，因此ChannelHandlerContext的事件流更短。
- 使用ChannelHandlerContext的API的时候，注意两点：1.ChannelHandlerContext和ChannelHandler之间的关系永远不会改变，缓存其引用是安全的；
  2.ChannelHandlerContext的方法将产生更短的事件流，尽可能利用这个特性。
- ChannelHandler安装到ChannelPipeline中的过程如下：
1.一个ChannelInitializer的实现被注册到BootStrap中；
2.当ChannelInitializer.initChannel()方法被调用时，ChannelInitializer将在ChannelPipeline中安装一组自定义的ChannelHandler；
3.ChannelInitializer将它自己从ChannelPipeline中移除；
- 线程池化模式可以理解为：1.从池中的空闲列表中选择一个Thread，并且指派它去运行一个已提交的任务；2.当任务完成时，将该Thread返回给该列表，以供下次使用。
- EventLoop由一个永远不会改变的Thread驱动，即一个EventLoop代表了一个线程。我们查看EventLoop的继承结构发现实际上我们使用的EventLoop最终都是继承了一个SingleThreadEventLoop，
也就是实际上EventLoop是一个单线程驱动的。
- EventLoop默认是与唯一一个线程绑定的，如果有任务提交到该EventLoop，那么将会放在任务队列中并由该确定的线程进行执行，
一个Channel只会与一个EventLoop绑定，一个EventLoop会绑定多个Channel。
- **由上方得知永远不要将一个长时间运行的任务放入到执行队列中，因为它将阻塞需要在同一个线程上执行的任何其他任务。如果必须要进行阻塞调用或者执行长时间运行的任务，建议使用EventExecutor**
- 注意：由于多个Channel共享一个EventLoop(即一个Thread)，所以ThreadLocal使用会有问题。
- 引导类根据作用不同分为服务器引导类和客户端引导类。服务端使用一个父的Channel来接受来自客户端的连接，并创建子 Channel 以用于它们之间的通信；而客户端将最可能只需要一个单独的、 没有父 Channel 的 Channel 来用于所有的网络交互。
- Bootstrap 类负责为客户端和使用无连接协议的应用程序创建 Channel
- 消息解码器：ByteToMessageDecoder会对入站数据进行缓冲，直到它准备好处理。

