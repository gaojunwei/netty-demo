package com.gjw.netty.demo.socket.server.handler;

import com.alibaba.fastjson.JSON;
import com.gjw.netty.demo.command.CommandWrapper;
import com.gjw.netty.demo.command.ap.RegisterConfirmData;
import com.gjw.netty.demo.command.enums.ApCmdEnum;
import com.gjw.netty.demo.command.enums.CmdEnum;
import com.gjw.netty.demo.config.SerialConfig;
import com.gjw.test.common.utils.BinaryUtil;
import com.gjw.test.common.utils.ByteUtils;
import com.gjw.test.common.utils.CRC8Utils;
import com.gjw.test.common.utils.CommandEscape;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: gaojunwei
 * @Date: 2018/11/29 17:24
 * @Description:
 */
public class ServerHandler extends SimpleChannelInboundHandler<StringBuilder> {
    private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);
    private AttributeKey<String> attributeKey = AttributeKey.valueOf("apMac");

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
    protected void channelRead0(ChannelHandlerContext ctx, StringBuilder stringBuilder) throws Exception {
        logger.info("server receive apMac:{},dao:",ctx.channel().attr(attributeKey).get(),stringBuilder.toString());
        dealCommand(ctx.channel(),stringBuilder);
        String apMac = ctx.channel().attr(attributeKey).get();
        System.out.println("attributeKey--apMac:"+apMac);
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


    /**
     * 递归处理指令结果
     */
    public void dealCommand(Channel channel,StringBuilder stringBuilder)
    {
        /**
         * 将消息格式不正确的数据移除
         */
        int headIndex = stringBuilder.indexOf(SerialConfig.MSG_HEAD);
        int tailIndex = stringBuilder.indexOf(SerialConfig.MSG_TAIL);
        //(1)消息格式不完整-不处理等待下次处理
        if(headIndex==-1 || tailIndex==-1)
        {
            logger.info("Serial dao analysis end reason:{},buffer:{}", "消息不完整暂不处理",stringBuilder);
            return;
        }
        //(2)消息格式错误-包尾在包头前--干掉第包头前的信息
        if (headIndex>tailIndex) {
            stringBuilder.replace(0, headIndex, "");
            dealCommand(channel,stringBuilder);
            return;
        }
        //(3)消息格式错误-包头后还有包头而不是包尾-干掉第二个包头前的信息
        int headIndex2 = stringBuilder.indexOf(SerialConfig.MSG_HEAD,headIndex+2);
        if((headIndex2!=-1) && headIndex2<tailIndex) {
            stringBuilder.replace(0, headIndex2, "");
            dealCommand(channel,stringBuilder);
            return;
        }

        int len = SerialConfig.MSG_HEAD.length();
        /**
         * 取出可处理的完整消息包
         */
        String cmdStr = stringBuilder.substring(headIndex, tailIndex+len).replaceAll("\\|","");
        stringBuilder.replace(0, tailIndex+len, "");//移除已处理的消息包信息
        //16进制字符串转二进制
        byte[] bytes = BinaryUtil.toBytes(cmdStr);
        /**
         * 反转义
         */
        byte[] bytess = CommandEscape.unescape(bytes);
        //取出包头
        byte header = bytess[0];
        //取出消息长度
        byte l1 = bytess[1];
        byte l2 = bytess[2];
        byte[] byteLength = new byte[2];
        byteLength[0] = l1;
        byteLength[1] = l2;
        short length = ByteUtils.getShort(byteLength);
        //校验字节数组长度是否合法
        if((length+5)!=bytess.length)
        {
            logger.info("Serial dao analysis fail dao:{},reason:{}",cmdStr, "dao length error");
            return;
        }
        //取出选项
        byte option = bytess[3];
        //取出命令区数据
        byte cmdByte = bytess[4];
        //取出校验位
        byte crc8Byte = bytess[length+3];
        /**
         * 校验校验位
         */
        //计算校验位
        byte[] crc8Bytes = new byte[length+2];//加上2字节长度的 长度区
        System.arraycopy(bytess, 1, crc8Bytes, 0, length+2);
        byte rcrc8Byte = CRC8Utils.calcCrc8(crc8Bytes);
        //取出数据区
        byte[] dateBytes = new byte[length-2];
        System.arraycopy(crc8Bytes, 4, dateBytes, 0, dateBytes.length);
        //取出包尾
        byte tailByte = bytess[length+4];
        //对比校验位
        if(rcrc8Byte!=crc8Byte)
        {
            logger.info("Serial dao analysis end dao:{},reason:{}",cmdStr, "crc8 error");
            return;
        }
        /**
         * 根据指令回调指定的指令处理器
         */
        logger.info("analysis success,包头:{},长度:{},选项:{},命令:{},数据区:{},校验:{},包尾:{}",
                BinaryUtil.byteToHex(header),BinaryUtil.bytesToHexFun3(byteLength),BinaryUtil.byteToHex(option),
                BinaryUtil.byteToHex(cmdByte),BinaryUtil.bytesToHexFun3(dateBytes),BinaryUtil.byteToHex(crc8Byte),BinaryUtil.byteToHex(tailByte));

        //判断指令类型
        if(cmdByte == CmdEnum.CMD_AP.getCmd())//ap类指令
        {
            //ap注册指令
            if(dateBytes[0] == ApCmdEnum.CMD_REG.getCmd()){
                byte[] macBytes = new byte[6];
                System.arraycopy(dateBytes, 1, macBytes, 0, macBytes.length);
                String apMac = BinaryUtil.bytesToHexFun3(macBytes);
                logger.info("Aa链接注册成功Mac地址:"+apMac);
                channel.attr(attributeKey).set(apMac);
                //返回注册成功通知
                RegisterConfirmData registerConfirmData = new RegisterConfirmData();
                CommandWrapper registerWrapper = new CommandWrapper(CmdEnum.CMD_AP.getCmd(),registerConfirmData.getBytes(),"regConfirm指令");
                byte[] dataBytes = registerWrapper.toBytes();
                ByteBuf byteBuf = Unpooled.buffer(dataBytes.length);
                byteBuf.writeBytes(dataBytes);
                ChannelFuture future = channel.writeAndFlush(byteBuf);
                logger.info("通知AP链接注册成功,{}",future.isSuccess());
            }else
            //异常报告
            if(dateBytes[0] == ApCmdEnum.CMD_REPORT.getCmd()){
                logger.info("AP端异常报告信息:{}",BinaryUtil.bytesToHexFun3(dateBytes));
            }else {
                logger.info("AP未知指令信息:{}",BinaryUtil.bytesToHexFun3(dateBytes));
            }
        }
        dealCommand(channel,stringBuilder);//递归继续处理
    }
}