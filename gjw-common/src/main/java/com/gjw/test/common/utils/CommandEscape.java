package com.gjw.test.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: duxuefu
 * @Date: 2018/3/19 16:00
 * @Description: 串口通信转义处理
 */
public class CommandEscape {

    private static final Logger logger = LoggerFactory.getLogger(CommandEscape.class);

    public static byte escapeByte = (byte) 0xCC;

    public static byte[] escapeBytes = {(byte) 0xAA, (byte) 0xCC, (byte) 0x55};

    /**
     * 转义二进制
     *
     * @param bytes
     * @return
     */
    public static byte[] escape(byte[] bytes) {
        //logger.info("CommandEscape_escape[转义二进制]_start bytes:{}", bytes);
        byte[] result = null;
        if (bytes != null && bytes.length > 0) {
            //logger.info("CommandEscape_escape[转义二进制] 处理输入的数组");
            // 确定新数组长度
            int newLength = bytes.length;
            //logger.info("CommandEscape_escape[转义二进制] 原数组长度:{}", newLength);
            for (byte in : bytes) {
                for (byte escape : escapeBytes) {
                    if (in == escape) {
                        newLength++;
                    }
                }
            }
            //logger.info("CommandEscape_escape[转义二进制] 新数组长度:{}", newLength);
            result = new byte[newLength];
            // 转义处理
            int j = 0;
            for (int i = 0; i < bytes.length; i++) {
                Byte escapeByte = null;
                for (byte escape : escapeBytes) {
                    if (bytes[i] == escape) {
                        escapeByte = escape;
                    }
                }
                if (escapeByte == null) {
                    //logger.info("CommandEscape_escape[转义二进制] 不需要转义:{}", bytes[i]);
                    result[j] = bytes[i];
                } else {
                    // 需要转义
                    //logger.info("CommandEscape_escape[转义二进制] 需要转义:{}", bytes[i]);
                    result[j] = CommandEscape.escapeByte;
                    j++;
                    result[j] = (byte) (escapeByte.byteValue() + 1);
                }
                j++;
            }
        }
        //logger.info("CommandEscape_escape[转义二进制]_end result:{}", result);
        return result;
    }

    /**
     * 反转义二进制
     *
     * @param bytes
     * @return
     */
    public static byte[] unescape(byte[] bytes) {
        //logger.info("CommandEscape_unescape[反转义二进制]_start bytes:{}", bytes);
        byte[] result = null;
        if (bytes != null && bytes.length > 0) {
            //logger.info("CommandEscape_unescape[反转义二进制] 处理输入的数组");
            // 确定新数组长度
            int newLength = bytes.length;
            //logger.info("CommandEscape_unescape[反转义二进制] 原数组长度:{}", newLength);
            for (byte in : bytes) {
                if (in == CommandEscape.escapeByte) {
                    newLength--;
                }
            }
            //logger.info("CommandEscape_unescape[反转义二进制] 新数组长度:{}", newLength);
            result = new byte[newLength];
            // 反转义处理
            int j = 0;
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] == CommandEscape.escapeByte) {
                    //logger.info("CommandEscape_unescape[反转义二进制] 需要转义:{}", bytes[i]);
                    result[j] = (byte) (bytes[++i] - 1);
                } else {
                    //logger.info("CommandEscape_unescape[反转义二进制] 不需要转义:{}", bytes[i]);
                    result[j] = (byte) bytes[i];
                }
                j++;
            }
        }
        //logger.info("CommandEscape_unescape[反转义二进制]_end bytes:{}", result);
        return result;
    }
}
