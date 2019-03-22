package com.jd.jr.innovation.epl.demo.command;

import com.jd.jr.innovation.epl.demo.command.enums.WrapperStatusEnum;
import com.jd.jr.innovation.epl.demo.config.SerialConfig;
import com.gjw.test.common.utils.ByteUtils;
import com.gjw.test.common.utils.CRC8Utils;
import com.gjw.test.common.utils.CommandEscape;
import lombok.Data;

/**
 * @author: duxuefu
 * @Date: 2018/3/19 14:05
 * @Description: AP通讯消息包装类
 */
@Data
public class CommandWrapper {

    private String cmdName;
    private WrapperStatusEnum statusEnum;//指令执行状态

    // 包头
    private byte header = SerialConfig.HEADER;
    // 长度
    private short length;
    // 选项
    private byte option = SerialConfig.OPTION;
    // 命令
    private byte cmd;
    // 数据区
    private byte[] data;
    // 校验
    private byte crc8;
    // 包尾
    private byte tail = SerialConfig.TAIL;

    // 完整消息数据
    private byte[] msgData;

    /**
     * 消息构造器
     * @param cmd 命令
     * @param data 数据区
     * @param cmdName 指令备注
     */
    public CommandWrapper(byte cmd, byte[] data,String cmdName) {
        this.cmd = cmd;
        this.data = data;
        // 计算长度 = 选项(1)+命令(1)+数据区(n)
        this.length = (short) (data.length + 2);
        this.statusEnum=WrapperStatusEnum.PENDING;//初始化发送状态
        this.cmdName = cmdName;
    }

    /**
     * 获取发送的完整消息数据
     * @return
     */
    public byte[] toBytes()
    {
        if(msgData!=null && msgData.length>0)
            return msgData;
        /**
         * 计算crc8
         */
        byte[] crc8Bytes = new byte[this.data.length + 4];
        // 设置 长度
        byte[] lengthBts = ByteUtils.shortToByteArray(this.length);
        System.arraycopy(lengthBts, 0, crc8Bytes, 0, lengthBts.length);
        // 设置 选项
        crc8Bytes[2] = this.option;
        // 设置 命令
        crc8Bytes[3] = this.cmd;
        // 设置 数据区
        System.arraycopy(data, 0, crc8Bytes, 4, data.length);
        this.crc8 = CRC8Utils.calcCrc8(crc8Bytes);

        /**
         * 转义
         */
        byte[] escapedData = CommandEscape.escape(crc8Bytes);
        byte[] crc8Escape = CommandEscape.escape(ByteUtils.byteToArray(this.crc8));

        /**
         * 封装整体消息数据
         */
        msgData = new byte[escapedData.length+crc8Escape.length+2];
        msgData[0] = this.header;
        System.arraycopy(escapedData, 0, msgData, 1, escapedData.length);
        System.arraycopy(crc8Escape, 0, msgData, escapedData.length+1, crc8Escape.length);
        msgData[escapedData.length+crc8Escape.length+1] = this.tail;
        return msgData;
    }
}
