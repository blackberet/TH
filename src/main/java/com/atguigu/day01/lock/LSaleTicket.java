package com.atguigu.day01.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: TSK
 * @Date：2022/12/7 18:50
 */
public class LSaleTicket {

    public static void main(String[] args) {
        final LTicket lTicket = new LTicket();

        //线程1
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    lTicket.sale();
                }
            }
        },"A");

        //线程2
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    lTicket.sale();
                }
            }
        },"B");

        //线程3
        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    lTicket.sale();
                }
            }
        },"C");

        thread1.start();
        thread2.start();
        thread3.start();
    }


}


class LTicket{
    //票数
    private int num = 30;

    //创建可重入锁
    private final ReentrantLock reentrantLock = new ReentrantLock();

    //卖票方法
    public  void sale(){

        //上锁
        reentrantLock.lock();
        try {
            //判断是否有票
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + ":卖出" + (num--) + "剩下:" + num);
            }
        } finally {
            //解锁
            reentrantLock.unlock();
        }

    }
}