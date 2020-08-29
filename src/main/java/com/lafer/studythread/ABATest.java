package com.lafer.studythread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABATest {

    //AtomicStampedReference 注意，如果泛型是一个整型包装类，注意对象引用问题，比较的是两个对象的引用，
    private static AtomicStampedReference<Integer> atomicInteger = new AtomicStampedReference(0, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            int stamp = atomicInteger.getStamp();
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " " + stamp);
                System.out.println(Thread.currentThread().getName() + " " + atomicInteger.compareAndSet(0, 1, stamp, stamp + 1));
                System.out.println(Thread.currentThread().getName() + " " + atomicInteger.getStamp());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            int stamp = atomicInteger.getStamp();
            System.out.println(Thread.currentThread().getName() + " " + stamp);
            System.out.println(Thread.currentThread().getName() + " " + atomicInteger.compareAndSet(0, 2, stamp, stamp + 1));
            System.out.println(Thread.currentThread().getName() + " " + atomicInteger.compareAndSet(2, 1, stamp + 1, stamp + 2));
            System.out.println(Thread.currentThread().getName() + " " + atomicInteger.getStamp());
        }).start();
    }

}
