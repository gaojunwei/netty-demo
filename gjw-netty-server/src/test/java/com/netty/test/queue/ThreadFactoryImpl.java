package com.netty.test.queue;

import java.util.concurrent.ThreadFactory;

/**
 * @author: gaojunwei
 * @Date: 2019/4/2 13:39
 * @Description:
 */
public class ThreadFactoryImpl implements ThreadFactory {

    private String taskId;

    public ThreadFactoryImpl(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r,String.format("Thread-%s",taskId));
        return thread;
    }
}