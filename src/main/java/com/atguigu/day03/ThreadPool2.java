package com.atguigu.day03;

import java.util.concurrent.*;

/**
 * @Author: TSK
 * @Date：2022/12/12 10:05
 */
public class ThreadPool2 {

    public static void main(String[] args) {

        /*
         * @Author TSK
         * @Date 2022/12/12 10:37
         *
         * corePoolSize 核心线程数
         * maximumPoolSize 最大线程数
         * keepAliveTime 线程存活时间
         * unit 时间单位
         * workQueue 采用什么阻塞队列存放我们未执行的任务
         * threadFactory 线程工厂
         * handler
         *
         */
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.AbortPolicy()
                );

        for (int i = 0; i < 8; i++) {
            final int finalI = i;
            threadPool.submit(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "柜台为" + finalI + "服务");
                }
            });
        }
        threadPool.shutdown();
    }
}
