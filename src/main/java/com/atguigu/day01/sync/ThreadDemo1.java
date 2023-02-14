package com.atguigu.day01.sync;

/**
 * @Author: TSK
 * @Date：2022/12/7 19:10
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        final Share share = new Share();
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


class Share{
    //初始值
    private int number = 0;

    //+1方法
    public synchronized void incr() throws InterruptedException {
        //第二步 判断 干活 通知
        while (number != 0){
            this.wait();
        }
        while (number == 0){
            number++;
        }
        System.out.println(Thread.currentThread().getName() + ":" + number);
        //通知其他线程
        this.notifyAll();


    }

    //-1方法
    public synchronized void decr() throws InterruptedException {

        while (number != 1) {
            this.wait();
        }

        while (number == 1) {
            number--;
        }
        System.out.println(Thread.currentThread().getName() + ":" + number);
        this.notifyAll();
    }


}
