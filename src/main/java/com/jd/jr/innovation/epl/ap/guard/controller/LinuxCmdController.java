package com.jd.jr.innovation.epl.ap.guard.controller;

import com.alibaba.fastjson.JSON;
import com.jd.jr.innovation.epl.ap.guard.common.SystemCode;
import com.jd.jr.innovation.epl.ap.guard.common.result.SingleResult;
import com.jd.jr.innovation.epl.ap.guard.common.util.UuidUtil;
import com.jd.jr.innovation.epl.ap.guard.service.cmd.LinuxCmdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: gaojunwei
 * @Date: 2018/11/13 16:43
 * @Description: 执行linux命令
 */
@RestController
@RequestMapping("linux")
public class LinuxCmdController {
    private static final Logger logger = LoggerFactory.getLogger(LinuxCmdController.class);

    @Resource
    private LinuxCmdService linuxCmdService;

    /**
     * 执行Linux命令功能
     * @param linuxCmd
     * @return
     */
    @RequestMapping(value ="cmd")
    public SingleResult<String> linuxCmd(@RequestParam(required = true) String linuxCmd) {
        String logId = UuidUtil.getUUID();
        logger.info("linuxCmd_start logId:{},linuxCmd:{}",logId,linuxCmd);
        SingleResult<String> result = new SingleResult<>();
        if(StringUtils.isEmpty(linuxCmd)){
            result.setCode(SystemCode.PARAMETER_ERROR.getCode());
            result.setMsg(SystemCode.PARAMETER_ERROR.getMsg());
            logger.info("linuxCmd_end logId:{},result:{}",logId, JSON.toJSONString(result));
            return result;
        }
        result = linuxCmdService.executeCmd(logId,linuxCmd);
        logger.info("linuxCmd_end logId:{},result:{}",logId, JSON.toJSONString(result));
        return result;
    }
}