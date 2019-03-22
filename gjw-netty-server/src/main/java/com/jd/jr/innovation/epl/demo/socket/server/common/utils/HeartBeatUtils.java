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
}