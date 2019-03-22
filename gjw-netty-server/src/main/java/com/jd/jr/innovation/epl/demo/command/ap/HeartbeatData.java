package com.jd.jr.innovation.epl.demo.command.ap;

import com.jd.jr.innovation.epl.demo.command.CommandData;
import com.jd.jr.innovation.epl.demo.config.SerialConfig;
import com.gjw.test.common.utils.ByteUtils;

/**
 * @author: gaojunwei
 * @Date: 2018/11/30 17:26
 * @Description: 心跳包数据
 */
public class HeartbeatData extends CommandData {

    @Override
    public byte[] getBytes() {
        super.setBytes(ByteUtils.byteToArray(SerialConfig.HEARTBEAT));
        return super.getBytes();
    }
}