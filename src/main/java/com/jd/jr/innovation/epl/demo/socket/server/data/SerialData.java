package com.jd.jr.innovation.epl.demo.socket.server.data;

import lombok.Data;

/**
 * @author: gaojunwei
 * @Date: 2018/12/4 10:13
 * @Description:
 */
@Data
public class SerialData {
    /**
     * 判断是否问题心跳包
     */
    Boolean heartBeat;

    /**
     * 16进制的消息包数据
     */
    String datahex;
}