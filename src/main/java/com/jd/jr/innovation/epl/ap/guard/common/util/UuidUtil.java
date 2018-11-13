package com.jd.jr.innovation.epl.ap.guard.common.util;

import java.util.UUID;

/**
 * 唯一标识工具类
 *
 * @author yangxiaofei
 * 2018-05-09
 */
public class UuidUtil {

    /**
     * 随机生成UUID
     *
     * @return UUID
     */
    public static synchronized String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
