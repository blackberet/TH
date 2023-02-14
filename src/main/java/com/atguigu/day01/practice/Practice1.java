package com.atguigu.day01.practice;


import java.util.Arrays;
import java.util.List;

/**
 * @Author: TSK
 * @Date：2022/12/9 17:03
 * 线程1打印：ABCDE,线程2打印：12345。 两个线程交叉输出打印。
 */
public class Practice1 {
    public static void main(String[] args) {
        final List<String> list1 = Arrays.asList("1", "2", "3", "4", "5");
        final List<String> list2 = Arrays.asList("A", "B", "C", "D", "E");

        final Practice1 practice1 = new Practice1();

        new Thread(new Runnable() {
            public void run() {
                synchronized (practice1){
                    for (String s : list1) {
                        System.out.print(s + " ");
                        try {
                            //先唤醒其他线程
                            practice1.notify();
                            //放开锁睡,另一个线程执行
                            practice1.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //打印一个,需要唤醒
                    practice1.notify();
                }
            }
        },"AA").start();


        new Thread(new Runnable() {
            public void run() {
                synchronized (practice1){
                    for (String s : list2) {
                        System.out.print(s + " ");
                        try {
                            practice1.notify();
                            practice1.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //打印一个,需要唤醒
                    practice1.notify();
                }
            }
        },"BB").start();
    }
}


