package com.netty.test;

import com.gjw.netty.demo.command.CommandWrapper;
import com.gjw.netty.demo.command.ap.HeartbeatData;
import com.gjw.netty.demo.command.enums.CmdEnum;
import com.gjw.test.common.utils.BinaryUtil;
import org.junit.Test;

/**
 * @author: gaojunwei
 * @Date: 2018/12/4 12:49
 * @Description:
 */
public class NettyTest {

    @Test
    public void test001(){
        HeartbeatData heartbeatData = new HeartbeatData();
        CommandWrapper commandWrapper = new CommandWrapper(CmdEnum.CMD_AP.getCmd(),heartbeatData.getBytes(),"");
        System.out.println(BinaryUtil.bytesToHexFun3(commandWrapper.toBytes()));

        byte crc = 46;
        System.out.println(BinaryUtil.byteToHex(crc));

    }
}