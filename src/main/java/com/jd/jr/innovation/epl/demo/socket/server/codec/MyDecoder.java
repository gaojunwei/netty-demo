package com.jd.jr.innovation.epl.demo.socket.server.codec;

import com.alibaba.fastjson.JSON;
import com.jd.jr.innovation.epl.demo.socket.server.config.FinalConfig;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.Map;

/**
 * 自己定义的协议
 *  数据包格式
 */
public class MyDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        // 可读长度必须大于基本长度
        if (buffer.readableBytes() < FinalConfig.BASE_LENGTH)//是用来当拆包时候剩余长度小于8的时候的保护，不加容易出错
        {
            return;
        }
        //以便后继的reset操作能恢复position位置
        buffer.markReaderIndex();
        byte StartFlage1 = buffer.readByte();
        byte StartFlage2 = buffer.readByte();
        int bodyLength = buffer.readInt();

        if(StartFlage1== FinalConfig.HEAD && StartFlage2== FinalConfig.HEAD && buffer.readableBytes()>=(bodyLength+2))
        {
            // 读取data数据
            byte[] data = new byte[bodyLength];
            buffer.readBytes(data);
            buffer.readByte();
            buffer.readByte();
            String json = new String(data, FinalConfig.CHARSET);
            System.out.println("-->>"+bodyLength+"  *** "+json);
            Map<String,Object> map = (Map<String,Object>)JSON.parse(json);
            out.add(map);
        }else{
            buffer.resetReaderIndex();
        }
    }

}