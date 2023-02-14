package com.atguigu.day03;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

/**
 * @Author: TSK
 * @Date：2022/12/12 14:13
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + "开进去");
                        Thread.sleep(4);
                        System.out.println(Thread.currentThread().getName() + "开出来");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        semaphore.release();
                    }
                }
            }, String.valueOf(i)).start();
        }
    }
}
