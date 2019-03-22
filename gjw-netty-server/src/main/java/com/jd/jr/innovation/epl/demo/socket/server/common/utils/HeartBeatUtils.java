package com.jd.jr.innovation.epl.demo.socket.server.common.utils;

import com.jd.jr.innovation.epl.demo.config.SerialConfig;
import org.springframework.util.StringUtils;

/**
 * @author: gaojunwei
 * @Date: 2018/12/4 14:49
 * @Description:
 */
public class HeartBeatUtils {

    /**
     * 过滤心跳包数据
     * @param serialData
     * @return 是否含有心跳包数据
     */
    public static boolean filterHeartBeat(StringBuilder serialData){
        if(StringUtils.isEmpty(serialData))
            return false;
        boolean flag = false;
        while (serialData.indexOf(SerialConfig.hexHeartBeatStrFormat)!=-1){
            int posStart = serialData.indexOf(SerialConfig.hexHeartBeatStrFormat);
            int posEnd = SerialConfig.hexHeartBeatStrFormat.length()+posStart;
            serialData.replace(posStart,posEnd,"");
            flag = true;
        }
        return flag;
    }

   /* public static void main(String[] args) {
        String ht = SerialConfig.hexHeartBeatStrFormat;

        String data = "fsdfsdfiwuye8rywe2hrihihfiduhfisuajfi".toUpperCase();

        data = "|AA00030004"+ht+ht+data+ht;

        StringBuilder stringBuilder = new StringBuilder(data);
        System.out.println("元1："+stringBuilder.toString());

        boolean flag = HeartBeatUtils.filterHeartBeat(stringBuilder);
        System.out.println("元2："+stringBuilder.toString() +"  "+ flag);
    }*/
}