package com.lafer.studythread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * 和其他的BlockingQueue 不一样，SynchronousQueue 不存储元素
 * put了一个元素，必须从里面take出来，否则不能再put进去。
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        // 同步队列
        BlockingQueue<String> queue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                queue.put("1");
                System.out.println(Thread.currentThread().getName() + " put 2");
                queue.put("2");
                System.out.println(Thread.currentThread().getName() + " put 3");
                queue.put("3");
                System.out.println(Thread.currentThread().getName() + " put 4");
                queue.put("4");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread1").start();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " take " + queue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " take "  + queue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " take " + queue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " take " + queue.take());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread2").start();
    }
}
