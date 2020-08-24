package com.lafer.studythread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {
    // 指定资源个数,多个线程来获取资源
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    //获取资源
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "获取资源");
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //释放资源
                    System.out.println(Thread.currentThread().getName() + "释放资源");
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
