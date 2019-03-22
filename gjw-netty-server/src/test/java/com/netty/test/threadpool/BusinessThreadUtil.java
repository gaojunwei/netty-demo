package com.netty.test.threadpool;

import com.gjw.test.common.utils.UuidUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: gaojunwei
 * @Date: 2019/3/22 16:11
 * @Description:
 */
public class BusinessThreadUtil {
    /**
     * 单例
     */
    private static BusinessThreadUtil businessThreadUtil = new BusinessThreadUtil();
    public static BusinessThreadUtil getInstance(){
        return businessThreadUtil;
    }
    //处理任务线程池
    private ExecutorService executor = new ThreadPoolExecutor(5, 100, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100000));//CPU核数4-10倍

    /**
     * 处理任务
     * @param msg
     */
    public void doTask(String msg) {
        executor.submit(()->{
            System.out.println(String.format("[%s:%s] %s-%s",Thread.currentThread().getId(),Thread.currentThread().getName(),msg,"come in..."));
            try {
                Thread.sleep(5*1000);
                int a = 0;
                int b = 0;
                System.out.println(a/b);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(String.format("[%s:%s] %s-%s",Thread.currentThread().getId(),Thread.currentThread().getName(),msg,"go out..."));
        });
    }

    public void closeThreadPool(){
        executor.shutdown();
        System.out.println("ThreadPool shutdown");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        for (int i = 0; i < 5; i++) {
            BusinessThreadUtil.getInstance().doTask(UuidUtil.getUUID());
        }
        Thread.sleep(10*1000);
        System.out.println("end-1");
        for (int i = 0; i < 5; i++) {
            BusinessThreadUtil.getInstance().doTask(UuidUtil.getUUID());
        }
        Thread.sleep(10*1000);
        System.out.println("end-2");
        //关闭线程池
        BusinessThreadUtil.getInstance().closeThreadPool();
        System.out.println("线程池关闭");
        for (int i = 0; i < 5; i++) {
            BusinessThreadUtil.getInstance().doTask(UuidUtil.getUUID());
        }
        Thread.sleep(10*1000);
        System.out.println("end-3");
    }
}