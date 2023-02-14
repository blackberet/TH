package com.atguigu.day01.lock;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: TSK
 * @Date：2022/12/7 20:27
 */
public class ThreadDemo4 {
    public static void main(String[] args) {

        //线程不安全,存在并发修改异常 ConcurrentModificationException
        //final List<String> list = new ArrayList<String>();

        //Vector线程安全,但是过时
        //final List<String> list = new Vector<String>();

        //Collections.synchronizedList(new ArrayList<String>());
        //工具类生成线程安全的list ,已过时
        //final List<String> list = Collections.synchronizedList(new ArrayList<String>());

        //CopyOnWriteArrayList
        //写时复制技术
        //list
        /*final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    list.add(UUID.randomUUID().toString().substring(0,8));
                    System.out.println(list);
                }
            },String.valueOf(i)).start();

        }*/


        //HashSet
        //final Set<String> set = new HashSet<String>();
        /*final CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<String>();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    set.add(UUID.randomUUID().toString().substring(0,8));
                    System.out.println(set);
                }
            },String.valueOf(i)).start();

        }*/





        //HashMap
        //ConcurrentModificationException
        //final Map<String, String> map = new HashMap<String, String>();
        final Map<String, String> map = new ConcurrentHashMap<String, String>();
        for (int i = 0; i < 10; i++) {
            final String key = String.valueOf(i);
            new Thread(new Runnable() {
                public void run() {
                    map.put(key,UUID.randomUUID().toString().substring(0,8));
                    System.out.println(map);
                }
            },String.valueOf(i)).start();

        }

    }
}
