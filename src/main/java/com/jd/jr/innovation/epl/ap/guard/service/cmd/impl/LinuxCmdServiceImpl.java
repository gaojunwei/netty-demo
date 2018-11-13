package com.jd.jr.innovation.epl.ap.guard.service.cmd.impl;

import com.alibaba.fastjson.JSON;
import com.jd.jr.innovation.epl.ap.guard.common.SystemCode;
import com.jd.jr.innovation.epl.ap.guard.common.result.SingleResult;
import com.jd.jr.innovation.epl.ap.guard.common.util.LinuxCmdUtils;
import com.jd.jr.innovation.epl.ap.guard.service.cmd.LinuxCmdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author: gaojunwei
 * @Date: 2018/11/13 16:04
 * @Description:
 */
@Service("linuxCmdService")
public class LinuxCmdServiceImpl implements LinuxCmdService {
    private static final Logger logger = LoggerFactory.getLogger(LinuxCmdServiceImpl.class);

    @Override
    public SingleResult<String> executeCmd(String logId, String linuxCmd) {
        logger.info("executeCmd_start logId:{},linuxCmd:{}",logId,linuxCmd);
        SingleResult<String> result = new SingleResult<>();
        String str = null;
        try {
            str = LinuxCmdUtils.execCmd(linuxCmd);
        } catch (Exception e) {
            logger.error("executeCmd_exception logId:{},errorMsg:{}",logId,e.getMessage());
            result.setCode(SystemCode.EXCEPTION.getCode());
            result.setMsg(SystemCode.EXCEPTION.getMsg());
            logger.error("executeCmd_end logId:{},result:{}",logId, JSON.toJSONString(result));
            return result;
        }
        result.setCode(SystemCode.SUCCESS.getCode());
        result.setMsg(SystemCode.SUCCESS.getMsg());
        result.setData(str);
        logger.error("executeCmd_end logId:{},result:{}",logId, JSON.toJSONString(result));
        return result;
    }
}