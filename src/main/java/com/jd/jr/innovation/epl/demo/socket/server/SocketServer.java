package com.jd.jr.innovation.epl.demo.socket.server;

import com.jd.jr.innovation.epl.demo.config.SysConfig;
import com.jd.jr.innovation.epl.demo.socket.server.codec.MyDecoder;
import com.jd.jr.innovation.epl.demo.socket.server.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: gaojunwei
 * @Date: 2018/11/29 17:15
 * @Description: Socket服务端
 */
@Component
@Order(value = 1)
public class SocketServer implements ApplicationRunner{
    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    @Autowired
    private SysConfig config;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception
    {
        EventLoopGroup bossGruop = new NioEventLoopGroup();//用于服务器端接受客户端的连接
        EventLoopGroup workGroup = new NioEventLoopGroup();//用于网络事件的处理
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGruop, workGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast("ping",new IdleStateHandler(20, 0, 0, TimeUnit.SECONDS));
                    ch.pipeline().addLast("decoder",new MyDecoder());
                    ch.pipeline().addLast(new ServerHandler());
                }
            }).option(ChannelOption.SO_BACKLOG, 1024);

            ChannelFuture channelFuture = bootstrap.bind(config.getSocketServerPort()).sync();
            if(channelFuture.isSuccess())
                logger.info("*******SocketServer start666 listen {}",config.getSocketServerPort());
            channelFuture.channel().closeFuture().sync();
        }finally
        {
            bossGruop.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}