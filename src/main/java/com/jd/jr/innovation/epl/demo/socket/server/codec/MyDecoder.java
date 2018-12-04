package com.jd.jr.innovation.epl.demo.socket.server.codec;

import com.jd.jr.innovation.epl.demo.command.CommandWrapper;
import com.jd.jr.innovation.epl.demo.command.ap.ApRegisterData;
import com.jd.jr.innovation.epl.demo.command.ap.HeartbeatData;
import com.jd.jr.innovation.epl.demo.command.enums.CmdEnum;
import com.jd.jr.innovation.epl.demo.config.SerialConfig;
import com.jd.jr.innovation.epl.demo.socket.server.SocketServer;
import com.jd.jr.innovation.epl.demo.socket.server.common.utils.BinaryUtil;
import com.jd.jr.innovation.epl.demo.socket.server.common.utils.HeartBeatUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 自己定义的协议
 *  数据包格式
 */
public class MyDecoder extends ByteToMessageDecoder {
    private static final Logger logger = LoggerFactory.getLogger(MyDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        //以便后继的reset操作能恢复position位置
        buffer.markReaderIndex();
        byte[] bytes = new byte[buffer.readableBytes()];
        buffer.readBytes(bytes);

        StringBuilder stringBuilder = new StringBuilder("");
        for (byte bt:bytes)
        {
            if (bt == SerialConfig.HEADER || bt == SerialConfig.TAIL)
            {
                stringBuilder.append("|");
            }
            stringBuilder.append(BinaryUtil.byteToHex(bt));
        }
        if(HeartBeatUtils.filterHeartBeat(stringBuilder)){
            HeartbeatData heartbeatData = new HeartbeatData();
            CommandWrapper commandWrapper = new CommandWrapper(CmdEnum.CMD_AP.getCmd(),heartbeatData.getBytes(),"h_t");
            //响应心跳包
            byte[] htBytes = commandWrapper.toBytes();
            logger.info("response heartBeat:{}",BinaryUtil.bytesToHexFun3(htBytes));
            ByteBuf htBuffer = ctx.alloc().buffer(htBytes.length);
            htBuffer.writeBytes(htBytes);
            ctx.channel().writeAndFlush(htBuffer);
        }
        if(stringBuilder.length()==0){
            return;
        }
        out.add(stringBuilder);
    }
}