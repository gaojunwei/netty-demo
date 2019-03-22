package com.gjw.netty.demo.command.ap;

import com.gjw.netty.demo.command.CommandData;
import com.gjw.netty.demo.command.enums.ApCmdEnum;
import com.gjw.test.common.utils.ByteUtils;

/**
 * @author: gaojunwei
 * @Date: 2018/12/19 13:10
 * @Description: ap链接注册成功通知
 */
public class RegisterConfirmData extends CommandData {
    @Override
    public byte[] getBytes() {
        super.setBytes(ByteUtils.byteToArray(ApCmdEnum.REGISTER_CONFIRM.getCmd()));
        return super.getBytes();
    }
}