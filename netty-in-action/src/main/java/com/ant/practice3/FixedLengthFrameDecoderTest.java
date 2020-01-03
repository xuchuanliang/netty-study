package com.ant.practice3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FixedLengthFrameDecoderTest {
    @Test
    public void test1(){
        ByteBuf byteBuf = Unpooled.buffer();
        for(int i=0;i<9;i++){
            byteBuf.writeByte(i);
        }
        ByteBuf input = byteBuf.duplicate();
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new MyByteToMessageDecoder(3));
        assertTrue(embeddedChannel.writeInbound(input));
        assertTrue(embeddedChannel.finish());
        ByteBuf read = embeddedChannel.readInbound();
        assertEquals(read.readableBytes(),3);
        read.release();
        read = embeddedChannel.readInbound();
        assertEquals(read.readableBytes(),3);
        read.release();
        read = embeddedChannel.readInbound();
        assertEquals(read.readableBytes(),3);
        read.release();
        assertNull(embeddedChannel.readInbound());
    }
}
