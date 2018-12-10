package com.jd.jr.innovation.epl.demo.command.enums;

/**
 * @author: gaojunwei
 * @Date: 2018/12/4 18:00
 * @Description: AP下属指令操作
 */
public enum  ApCmdEnum {
    CMD_HT((byte) 0x01, "AP心跳指令"),
    CMD_REG((byte) 0x02, "Ap注册指令"),
    CMD_REPORT((byte) 0x03, "Ap返回异常报告");

    private byte cmd;
    private String desc;

    ApCmdEnum(byte cmd, String desc) {
        this.cmd = cmd;
        this.desc = desc;
    }

    public byte getCmd() {
        return cmd;
    }

    public void setCmd(byte cmd) {
        this.cmd = cmd;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}