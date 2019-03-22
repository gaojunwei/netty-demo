package com.gjw.netty.demo.config;

import com.gjw.test.common.utils.BinaryUtil;

/**
 * @author: gaojunwei
 * @Date: 2018/11/30 16:48
 * @Description: 串口数据配置文件
 */
public interface SerialConfig {
    /**
     * 包头
     */
    byte HEADER = (byte) 0xAA;

    /**
     * 包尾
     */
    byte TAIL = (byte) 0x55;

    /**
     * 选项数据区
     */
    byte OPTION = (byte) 0x00;

    /**
     * 心跳包数据
     */
    byte HEARTBEAT = (byte) 0x01;

    /**
     * 心跳包数据
     */
    String hexHeartBeatStrFormat = "|AA00030004012E|55";

    String MSG_HEAD = "|".concat(BinaryUtil.byteToHex(HEADER).toUpperCase());//16进制包头
    String MSG_TAIL = "|".concat(BinaryUtil.byteToHex(TAIL).toUpperCase());//16进制包头
}