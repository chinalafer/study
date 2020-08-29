package com.lafer.studythread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

    Lock lockA = new ReentrantLock();

    Lock lockB = new ReentrantLock();

    public void getLockAB() {
        lockA.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " get lockA ");
            TimeUnit.SECONDS.sleep(2);
            lockB.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " get lockB ");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lockB.unlock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockA.unlock();
        }
    }

    public void getLockBA() {
        lockB.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " get lockA ");
            TimeUnit.SECONDS.sleep(2);
            lockA.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " get lockB ");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lockA.unlock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockB.unlock();
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(() -> {
            deadLock.getLockAB();
        }, "Thread1").start();
        new Thread(() -> {
            deadLock.getLockBA();
        }, "Thread2").start();
    }

}
