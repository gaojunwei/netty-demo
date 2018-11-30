package com.jd.jr.innovation.epl.demo.socket.server.codec;

import com.jd.jr.innovation.epl.demo.socket.server.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 自己定义的协议 数据包格式
 */
public class MyEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext tcx, Message msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.getStartFlage());
        out.writeInt(msg.getBodyLength());
        out.writeBytes(msg.getBodyData());
        out.writeBytes(msg.getEndFlage());
    }
}
