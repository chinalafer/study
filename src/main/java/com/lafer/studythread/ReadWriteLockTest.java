package com.lafer.studythread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁（写锁） 一次只能有一个线程占有
 * 共享锁（读锁） 多个线程可以同时占有
 * ReadWriteLock
 * 读-读 可以共存
 * 读-写 不可以共存
 * 写-写 不可以共存
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 10; i++) {
            if ((i & 1) == 0) {
                int finalI = i;
                new Thread(() -> {
                    myCache.put(String.valueOf(finalI), finalI);
                }, String.valueOf(i)).start();
            } else {
                int finalI = i;
                new Thread(() -> {
                    myCache.get(String.valueOf(finalI));
                }, String.valueOf(i)).start();
            }
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    public void put(String key, Object value) {
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入开始");
            map.put(key, value);
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "写入完毕");
            reentrantReadWriteLock.writeLock().unlock();
        }
    }
    public void get(String key) {
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取开始");
            map.get(key);
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "读取完毕");
            reentrantReadWriteLock.readLock().unlock();
        }
    }
}
