package com.netty.test;

import com.alibaba.fastjson.JSON;
import com.gjw.netty.demo.command.CommandWrapper;
import com.gjw.netty.demo.command.ap.HeartbeatData;
import com.gjw.netty.demo.command.enums.CmdEnum;
import com.gjw.test.common.utils.BinaryUtil;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author: gaojunwei
 * @Date: 2018/12/4 12:49
 * @Description:
 */
public class NettyTest {

    class Aa{
         int a;
         int b;
        int c = 3;

        public Aa(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    @Test
    public void test002() throws IllegalAccessException {
        Aa aa = new Aa(1,2);
        Class<?> aaClass = aa.getClass();
        Field[] fields = aaClass.getDeclaredFields();
        System.out.println(JSON.toJSONString(fields));
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName());
            System.out.println(fields[i].get(aa));
        }


    }
    @Test
    public void test001(){
        HeartbeatData heartbeatData = new HeartbeatData();
        CommandWrapper commandWrapper = new CommandWrapper(CmdEnum.CMD_AP.getCmd(),heartbeatData.getBytes(),"");
        System.out.println(BinaryUtil.bytesToHexFun3(commandWrapper.toBytes()));

        byte crc = 46;
        System.out.println(BinaryUtil.byteToHex(crc));

    }
}