package com.jd.jr.innovation.epl.demo.socket.server.config;

public interface FinalConfig {
    byte HEAD = (byte) 0xaa;
    byte END1 = (byte) 0x0d;//'\r'
    byte END2 = (byte) 0x0a;//'\n'
    int BASE_LENGTH = 8;
    String CHARSET = "utf-8";
    String HEART_BEAT = "h_t";

    /**指令参数 - start*/
    String ROLL_NONE = "roll_none";//空广播包，用来价签端收集AP信号强度
    String ROLL_BATCH = "call-batch";//按照批点名
    String ROLL_ALL = "call-over";//全点名（通过index保证点过的不会再次连接）
    String ROLL_SINGLE = "roll_single";//单个点名
    String DISCONNECT = "ap-disconnect";//断开连接

    String CALL_OVER = "call-over";//全点名（废弃）

    String CHANGE_IMG = "change_img";//变更展示图片指令标识
    String TIME_SYNC = "time_sync";//时间同步指令标识
    String REPORT_MAC = "report_mac";//上报mac
    String REPORT_STATE = "report_state";//上报状态
    String AP_INFO = "ap_info";//获取AP设备信息指令

    String DEVICE_OTA = "device_ota";//代表系统OTA升级功能

    String DEVICE_AP = "ap";//设备类型:ap
    String DEVICE_EPL = "epl";//设备类型:epl
    String DEVICE_CA = "ca";//设备类型:smp

    String COMMAND_LINE = "command_line";// 执行命令

    /**指令参数 - end*/





    /**上报状态指令中reason 值设定（要求：云端与AP软件端保持一致） - start*/
    String REASON_SUCCESS = "success";//指令执行成功
    String REASON_FAIL = "fail";//指令执行失败
    String REASON_TIME_OUT = "time_out";//指令执行超时（云端按成功处理）
    String REASON_AP_BUSY = "ap_busy";//AP忙碌状态（更新指令的发送时间戳）
    String REASON_SERIAL_UNAVAILABLE = "serial_unavailable";//串口连接不可用(待确定)
    String REASON_DOING = "running";//指令正在执行中（针对点名指令设计，更新云端指令的发送时间戳）
    String REASON_RECEIVED = "received";//已接收该指令（针对变图指令设计，移除云端该变图指令消息）
    /**上报状态指令中reason 值设定 - end*/
}
