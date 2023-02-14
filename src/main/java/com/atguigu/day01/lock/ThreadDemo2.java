package com.atguigu.day01.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: TSK
 * @Date：2022/12/7 19:10
 */
public class ThreadDemo2 {
    public static void main(String[] args) {
         final Share1 share = new Share1();
        new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i <= 10; i++) {
                        share.incr();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i <= 10; i++) {
                        share.decr();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i <= 10; i++) {
                        share.incr();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i <= 10; i++) {
                        share.decr();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}


class Share1{
    //初始值
    private int number = 0;

    //创建lock
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //+1方法
    public void incr() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + ":" + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    //-1方法
    public void decr() throws InterruptedException {

        lock.lock();
        try {
            while (number != 1) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + ":" + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }


}
