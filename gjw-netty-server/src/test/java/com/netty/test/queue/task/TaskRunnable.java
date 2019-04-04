package com.netty.test.queue.task;

/**
 * @author: gaojunwei
 * @Date: 2019/4/2 13:45
 * @Description:
 */
public class TaskRunnable implements Runnable {

    private String id;

    public TaskRunnable(String id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(String.format("%s %s : start",id,Thread.currentThread().getName()));
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s %s : end",id,Thread.currentThread().getName()));
    }
}