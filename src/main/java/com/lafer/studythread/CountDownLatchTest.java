package com.lafer.studythread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// 计数器
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        // 总数是5
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                // 数量 -1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        // 等待计数器归零， 然后再向下执行
        countDownLatch.await();
        System.out.println("end");
    }
}
