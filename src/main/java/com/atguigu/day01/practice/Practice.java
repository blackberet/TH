package com.atguigu.day01.practice;


import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: TSK
 * @Date：2022/12/9 16:28
 */
/*
 * @Author TSK
 * @Date 2022/12/9 16:28
 *实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，
 * 线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *
 */
public class Practice {
    public static void main(String[] args) {
        final Container container = new Container();
        new Thread(new Runnable() {
            public void run() {
                synchronized (container) {
                    //添加十个元素
                    for (int i = 1; i <= 10; i++) {
                        try {
                            //判断元素个数是否等于5
                            if (container.size() == 5){
                                //等于5等待
                                container.wait();
                            }
                            //不等于5添加
                            container.add(i);
                            System.out.println(Thread.currentThread().getName() + "添加第" + i + "个" );
                            //通知下个线程
                            container.notify();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },"AA").start();


        new Thread(new Runnable() {
            public void run() {
                synchronized (container) {
                    if (container.size()!=5) {
                        try {
                            container.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + "结束");
                    container.notify();
                }
            }
        },"BB").start();


    }
}

class Container{
    final CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<Integer>();

    public void add(Integer i){
        list.add(i);
    }

    public Integer size(){
        return list.size();
    }

}
