package com.gjw.test.common.utils;

import java.util.UUID;


/**
 * UUID工具类
 */
public class UuidUtil {
    /**
     * 随机生成UUID
     *
     * @return
     */
    public static synchronized String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
