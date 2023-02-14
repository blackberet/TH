package com.atguigu.day01;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Author: TSK
 * @Date：2022/12/8 10:12
 */
public class Demo1 {

    public static void main(String[] args) {
        //runnable
        new Thread(new MyThread1(),"AA").start();

        new Thread(new FutureTask(new MyThread2()),"BB").start();

    }

}


class MyThread1 implements Runnable{

    public void run() {

    }
}


class MyThread2 implements Callable {

    public Integer call() throws Exception {
        return 200;
    }
}

