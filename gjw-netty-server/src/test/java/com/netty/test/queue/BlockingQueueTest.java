package com.netty.test.queue;

import com.alibaba.fastjson.JSON;
import com.gjw.test.common.utils.UuidUtil;
import com.netty.test.queue.task.TaskRunnable;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author: gaojunwei
 * @Date: 2019/4/2 11:39
 * @Description:
 */
public class BlockingQueueTest {
    private static final Logger logger = LoggerFactory.getLogger(BlockingQueueTest.class);
    String taskId = UuidUtil.getUUID();

    ExecutorService executorService = new ThreadPoolExecutor(1, 2, 60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(5,true),
            new ThreadFactoryImpl(),new RejectedExecutionHandlerImpl());

    @Test
    public void test001() throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            executorService.execute(new TaskRunnable(String.valueOf(i)));
        }
        Thread.sleep(10000*1000);
    }

    /**
     * 线程池
     */
    private ExecutorService newFixedThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,10L,
            TimeUnit.SECONDS,new SynchronousQueue<Runnable>(true));

    @Test
    public void test002() throws InterruptedException, TimeoutException, ExecutionException {
        List<Future<Map<String,String>>> futures = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String uuid = UuidUtil.getUUID();

            Future<Map<String,String>> future = newFixedThreadPool.submit(()->dealHandler(uuid));
            logger.info("producer:"+uuid);
            futures.add(future);
        }

        for(Future<Map<String,String>> future : futures){
            Map<String,String> result = future.get(5,TimeUnit.SECONDS);
            logger.info("运行结果"+ JSON.toJSONString(result));
        }
    }
    private Map<String,String> dealHandler(String a) throws InterruptedException {
        logger.info("sleep start");
        Thread.sleep(1000);
        logger.info("sleep end");
        Map<String,String> map = new HashMap<>();
        map.put("key",a);
        return map;
    }
}