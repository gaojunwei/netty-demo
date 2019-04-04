package com.netty.test.queue;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: gaojunwei
 * @Date: 2019/4/2 14:01
 * @Description:
 */
public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(String.format("溢出 taskId"));
        Thread thread = new Thread(r);
        thread.start();
    }
}