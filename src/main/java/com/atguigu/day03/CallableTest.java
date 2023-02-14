package com.atguigu.day03;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: TSK
 * @Date：2022/12/12 11:35
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        new Thread(new MyRunnable(),"runnable").start();

        FutureTask<String> futureTask = new FutureTask<String>(new MyCallable());
        new Thread(futureTask,"callable").start();

        //阻塞结果可以服用,call方法的结果
        System.out.println(futureTask.get());
    }
}

class MyRunnable implements Runnable{
    public void run() {
        System.out.println("Runnable");
    }
}

class MyCallable implements Callable<String>{
    public String call() throws Exception {
        System.out.println("callable");
        return "是callable";
    }
}