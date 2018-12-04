package com.jd.jr.innovation.epl.demo.socket.server.handler;

import com.alibaba.fastjson.JSON;
import com.jd.jr.innovation.epl.demo.command.CommandWrapper;
import com.jd.jr.innovation.epl.demo.command.ap.HeartbeatData;
import com.jd.jr.innovation.epl.demo.command.enums.CmdEnum;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author: gaojunwei
 * @Date: 2018/11/29 17:24
 * @Description:
 */
public class ServerHandler extends SimpleChannelInboundHandler<StringBuilder> {
    private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
        logger.info("ServerHandler_channelActive_start {}",JSON.toJSONString(ctx));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelInactive();
        logger.info("ServerHandler_channelInactive_start {}",JSON.toJSONString(ctx));
        ctx.channel().writeAndFlush("");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StringBuilder msg) throws Exception {
        System.out.println("server receive data:"+msg.toString());
    }

    /**
     * 读写超时事事件
     * 如果7秒没有触发服务端的read事件，判定客户端超时关闭客户端链接
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            //如果读超时
            if(event.state()== IdleState.READER_IDLE) {
                logger.info("ServerHandler_userEventTriggered client time out,close client connect");
                ctx.channel().close();
            }
        }else {
            super.userEventTriggered(ctx,evt);
        }
    }
}