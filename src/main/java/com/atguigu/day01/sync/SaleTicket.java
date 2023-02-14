package com.atguigu.day01.sync;

/**
 * @Author: TSK
 * @Date：2022/12/7 18:35
 */
public class SaleTicket {

    //多个线程,卖票
    public static void main(String[] args) {
        final Ticket ticket = new Ticket();
        //线程1
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "A");

        //线程2
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "B");

        //线程3
        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "C");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}



class Ticket{
    //票数
    private int num = 30;

    public synchronized void sale(){
        //判断是否有票
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + ":卖出" + (num--) + "剩下:" + num);
        }
    }
}
