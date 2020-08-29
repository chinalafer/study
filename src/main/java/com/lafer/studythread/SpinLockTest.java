package com.lafer.studythread;

import com.lafer.study.Test;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLockTest {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        System.out.println(Thread.currentThread().getName() + " - myLock");
        while (atomicReference.compareAndSet(null, Thread.currentThread())) {

        }
    }

    public void myUnLock() {
        System.out.println(Thread.currentThread().getName() + " - myUnLock");
        atomicReference.compareAndSet(Thread.currentThread(), null);
    }

    public static void main(String[] args) {
        SpinLockTest spinLockTest = new SpinLockTest();
        new Thread(() -> {
            try {
                spinLockTest.myLock();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLockTest.myUnLock();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                spinLockTest.myLock();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLockTest.myUnLock();
            }
        }, "B").start();
    }

}
