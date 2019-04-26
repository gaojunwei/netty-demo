package com.netty.test.queue;

import java.util.concurrent.ThreadFactory;

/**
 * @author: gaojunwei
 * @Date: 2019/4/2 13:39
 * @Description:
 */
public class ThreadFactoryImpl implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r,"eplSyncSchedule-");
        return thread;
    }
}