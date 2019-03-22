package com.gjw.netty.demo.command.enums;

/**
 * @author: duxuefu
 * @Date: 2018/3/19 10:27
 * @Description: AP控制指令
 */
public enum ApTypeEnum {

    AP_PING((byte)0, "链路测试命令"),
    AP_PONG_RESPONSE((byte)1, "回复端链路测试命令(上报)"),
    AP_DISCONNECT((byte)2, "断连命令"),
    AP_LOGON_RESPONSE((byte)3, "AP_BLE连接上了价签端(上报)"),
    AP_LOGOFF_RESPONSE((byte)4, "AP_BLE断开了价签端(上报)"),
    AP_OTA((byte)5, "在线升级命令"),
    AP_REST((byte)6, "将AP置为空闲IDEL状态"),
    AP_ROLL((byte)7, "广播"),
    AP_LOG((byte)8, "重要Log信息返回"),
    AP_REBOOT((byte)9, "重启AP BLE"),
    AP_TYPE_ACK((byte)0x0b, "回应数据包");

    ApTypeEnum(byte code, String name) {
        this.code = code;
        this.name = name;
    }

    private byte code;
    private String name;

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
