package com.jd.jr.innovation.epl.ap.guard.common.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author gaojunwei
 * @create 2018-04-23 17:25
 * @desc 执行linux命令
 **/
public class LinuxCmdUtils {

    /**
     * 执行系统命令, 返回执行结果
     * @param cmd 需要执行的命令
     */
    public static String execCmd(String cmd) throws Exception {
        StringBuilder result = new StringBuilder();

        Process process = null;
        BufferedReader bufrIn = null;
        BufferedReader bufrError = null;

        try {
            // 执行命令, 返回一个子进程对象（命令在子进程中执行）
            process = Runtime.getRuntime().exec(cmd);
            // 方法阻塞, 等待命令执行完成（成功会返回0）
            process.waitFor();
            // 获取命令执行结果, 有两个结果: 正常的输出 和 错误的输出（PS: 子进程的输出就是主进程的输入）
            bufrIn = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            bufrError = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));

            // 读取输出
            String line = null;
            while ((line = bufrIn.readLine()) != null) {
                result.append(line).append("<br/>");
            }
            while ((line = bufrError.readLine()) != null) {
                result.append(line).append("<br/>");
            }
        } finally {
            closeStream(bufrIn);
            closeStream(bufrError);
            // 销毁子进程
            if (process != null) {
                process.destroy();
            }
        }
        // 返回执行结果
        return result.toString();
    }

    /**
     * 关闭流
     * @param stream
     */
    private static void closeStream(Closeable stream) throws IOException {
        if (stream != null) {
            stream.close();
        }
    }
}