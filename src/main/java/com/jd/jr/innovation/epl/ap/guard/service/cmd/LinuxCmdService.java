package com.jd.jr.innovation.epl.ap.guard.service.cmd;

import com.jd.jr.innovation.epl.ap.guard.common.result.SingleResult;

/**
 * @author gaojunwei
 * @create 2018-11-13 16:03
 * @desc 执行linux命令
 **/
public interface LinuxCmdService {
    /**
     * 执行linux命令
     * @param logId 日志ID
     * @param linuxCmd 命令
     * @return
     */
    SingleResult<String> executeCmd(String logId,String linuxCmd);
}
