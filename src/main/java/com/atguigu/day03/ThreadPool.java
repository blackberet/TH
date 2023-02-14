package com.atguigu.day03;

import java.sql.Time;
import java.util.concurrent.*;

/**
 * @Author: TSK
 * @Date：2022/12/12 10:05
 */
public class ThreadPool {

    public static void main(String[] args) {

        /*1.创建一个固定数量的线程池
            适用于执行长期任务
         */
        ExecutorService threadPool1 = Executors.newFixedThreadPool(3);

        /*2.创建一个单个线程的线程池
            任何时候只有一个线程在执行,保证了线程执行任务的顺序性
         */
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();

        /*3.创建一个可扩展的线程池
            创建尽可能多的的线程,显著提高短时异步任务
         */
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        /*4.创建可以定时操作的线程池
            具备定时任务的功能
         */
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(3);

        //new ThreadPoolExecutor(8,1, TimeUnit.SECONDS,new LinkedBlockingQueue())


        for (int i = 0; i < 100; i++) {
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
