package com.jd.jr.innovation.epl.demo.socket.server.handler;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author: gaojunwei
 * @Date: 2018/11/29 17:24
 * @Description:
 */
public class ServerHandler extends SimpleChannelInboundHandler<Map<String, Object>> {
    private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Map<String, Object> msg) throws Exception {
        logger.info("ServerHandler_channelRead0_start 收到数据 msg:{}", JSON.toJSONString(msg));
    }
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        logger.info("ServerHandler_channelRegistered_start 注册链接 {}",JSON.toJSONString(ctx));
        System.out.println(ctx.name());
    }
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        logger.info("ServerHandler_channelUnregistered_start 链接关闭 {}",JSON.toJSONString(ctx));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
        logger.info("ServerHandler_channelActive_start 激活链接 {}",JSON.toJSONString(ctx));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelInactive();
        logger.info("ServerHandler_channelInactive_start 关闭链接 {}",JSON.toJSONString(ctx));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        ByteBuf buf=(ByteBuf) msg;
        byte[] req=new byte[buf.readableBytes()];
        buf.readBytes(req);
        System.out.println("服务器端接收的消息："+new String(req,"UTF-8"));
    }

    /**
     * 按空闲事件类型处理
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // IdleStateHandler 所产生的 IdleStateEvent 的处理逻辑.
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case READER_IDLE:
                    System.out.println("READER_IDLE");
                    break;
                case WRITER_IDLE:
                    System.out.println("WRITER_IDLE");
                    break;
                case ALL_IDLE:
                    System.out.println("ALL_IDLE");
                    break;
                default:
                    break;
            }
        }
    }

}