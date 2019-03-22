package com.jd.jr.innovation.epl.demo.command.ap;

import com.jd.jr.innovation.epl.demo.command.CommandData;
import com.jd.jr.innovation.epl.demo.command.enums.ApCmdEnum;
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