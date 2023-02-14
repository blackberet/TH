package com.atguigu.day03;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: TSK
 * @Date：2022/12/12 14:03
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
            public void run() {
                System.out.println("推塔");
            }
        });

        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + finalI +  "就位");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }, String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName() + "推塔");
    }
}
