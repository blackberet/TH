package com.atguigu.day03;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: TSK
 * @Date：2022/12/12 14:33
 */
public class ForJoinTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(1, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);
        //阻塞方法
        System.out.println(submit.get());

    }
}

class MyTask extends RecursiveTask<Integer>{
    private int begin;
    private int end;
    private int result;
    private int THRESHOLD = 5;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    //做任务
    @Override
    protected Integer compute() {
        if ((end - begin) < THRESHOLD){
            for (int i = begin; i <= end; i++) {
                result += i;
            }

        }else {
            //1+2+3+4+...+100
            //拆分需要拿到中间值
            int mid = (begin + end) / 2;
            MyTask myTask1 = new MyTask(begin, mid);
            MyTask myTask2 = new MyTask(mid + 1, end);
            //分配任务进行执行
            myTask1.fork();
            myTask2.fork();
            result = myTask1.join() + myTask2.join();
        }
        return result;

    }
}
