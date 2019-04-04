package com.netty.test.queue;

import com.gjw.test.common.utils.UuidUtil;
import com.netty.test.queue.task.TaskRunnable;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author: gaojunwei
 * @Date: 2019/4/2 11:39
 * @Description:
 */
public class BlockingQueueTest {

    String taskId = UuidUtil.getUUID();

    ExecutorService executorService = new ThreadPoolExecutor(1, 2, 60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(5,true),
            new ThreadFactoryImpl(taskId),new RejectedExecutionHandlerImpl());

    @Test
    public void test001() throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            executorService.execute(new TaskRunnable(String.valueOf(i)));
        }
        Thread.sleep(10000*1000);
    }
}