package com.gjw.netty.demo.command.enums;

/**
 * @author: duxuefu
 * @Date: 2018/3/19 10:27
 * @Description: EPL控制指令
 */
public enum EplTypeEnum {

    EPL_TIME_UPDATE((byte) 0, "更新时间"),
    EPL_IMAGE_UPDATE((byte) 1, "更新图片数据到FLASH"),
    EPL_IMAGE_SHOW((byte) 2, "图片显示"),
    EPL_IMAGE_CANCEL((byte) 3, "图片显示取消"),
    EPL_OTA((byte) 4, "在线升级空中升级"),
    EPL_TYPE_ACK((byte) 5, "回应数据包"),
    EPL_LOGON((byte) 6, "登入(上报)");

    EplTypeEnum(byte code, String name) {
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
