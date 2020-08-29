package com.lafer.studythread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class JMMTest {
    private static boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
//        new Thread(() -> {
//            while (flag) {
//
//            }
//        }).start();
//        TimeUnit.SECONDS.sleep(2);
//        new Thread(() -> {
//            flag = false;
//        }).start();
        test();
    }

    private static AtomicInteger num = new AtomicInteger(0);

    private static void add() {
        num.getAndIncrement();
    }

    private static void test() {
        for (int i = 0; i < 2000; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 10000; i1++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
