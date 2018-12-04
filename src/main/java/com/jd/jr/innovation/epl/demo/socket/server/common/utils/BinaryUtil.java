package com.jd.jr.innovation.epl.demo.socket.server.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: duxuefu
 * @Date: 2018/2/2 16:55
 * @Description:
 */
public class BinaryUtil {

    private static final Logger logger = LoggerFactory.getLogger(BinaryUtil.class);

    /**
     * 16进制表示的字符串转化为二进制数组
     *
     * @param str
     * @return
     */
    public static byte[] toBytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    /**
     * 二进制数组转化为16进制表示的字符串(大写)
     *
     * @param bytes
     * @return
     */
    public static String bytesToHexFun3(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) { // 使用String的format方法进行转换
            buf.append(String.format("%02x", new Integer(b & 0xff)));
        }
        return buf.toString().toUpperCase();
    }

    /**
     * byte转化为16进制表示的字符串
     * @param b
     * @return
     */
    public static String byteToHex(byte b) {
        StringBuilder buf = new StringBuilder();
        buf.append(String.format("%02x", new Integer(b & 0xff)));
        return buf.toString().toUpperCase();
    }

}
