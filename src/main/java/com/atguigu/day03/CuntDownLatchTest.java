package com.atguigu.day03;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: TSK
 * @Date：2022/12/12 14:07
 */
public class CuntDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()  + finalI + "同学离开");
                    //计数器减一
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "班长离开");
    }
}
