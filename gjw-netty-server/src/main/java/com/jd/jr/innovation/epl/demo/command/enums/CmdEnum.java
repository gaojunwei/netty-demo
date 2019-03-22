package com.jd.jr.innovation.epl.demo.command.enums;

/**
 * @author: duxuefu
 * @Date: 2018/3/19 10:27
 * @Description: 串口指令枚举类
 */
public enum CmdEnum {

    CMD_BLE((byte) 0x02, "AP BLE指令"),
    CMD_EPL((byte) 0x03, "EPL指令"),
    CMD_AP((byte) 0x04, "AP指令");

    CmdEnum(byte cmd, String desc) {
        this.cmd = cmd;
        this.desc = desc;
    }

    private byte cmd;
    private String desc;

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
