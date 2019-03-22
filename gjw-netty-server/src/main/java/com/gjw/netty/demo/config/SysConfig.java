package com.gjw.netty.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: gaojunwei
 * @Date: 2018/11/29 17:43
 * @Description: 系统配置文件
 */
@Data
@Component
public class SysConfig {
    /**
     * 服务端监听端口号
     */
    @Value("${socket.server.port}")
    private Integer socketServerPort;
}