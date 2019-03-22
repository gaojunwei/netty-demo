package com.gjw.test.common.utils;

/**
 * @author gaojunwei
 * @create 2018-03-19 14:14
 * @desc 工具类
 **/
public class ByteUtils {
    /**
     * short类型转字节数组
     *
     * @param s
     * @return
     */
    public static byte[] shortToByteArray(short s) {
        byte[] shortBuf = new byte[2];
        for (int i = 0; i < 2; i++) {
            int offset = (shortBuf.length - 1 - i) * 8;
            shortBuf[i] = (byte) ((s >>> offset) & 0xff);
        }
        return shortBuf;
    }

    /**
     * 将单个字节变为字节数组
     *
     * @param b
     * @return
     */
    public static byte[] byteToArray(byte b) {
        byte[] bytes = new byte[1];
        bytes[0] = b;
        return bytes;
    }

    public static short getShort(byte[] b) {
        return (short) ((b[0] << 8) | b[1] & 0xff);
    }

    /**
     * 高位在前，低位在后
     * @param num
     * @return
     */
    public static byte[] int2bytes(int num) {
        byte[] result = new byte[4];
        result[0] = (byte) ((num >>> 24) & 0xff);//说明一
        result[1] = (byte) ((num >>> 16) & 0xff);
        result[2] = (byte) ((num >>> 8) & 0xff);
        result[3] = (byte) ((num >>> 0) & 0xff);
        return result;
    }

    /**
     * byte 数组与 int 的相互转换
     */
    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }
}
