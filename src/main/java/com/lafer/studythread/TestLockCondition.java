package com.lafer.studythread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockCondition {

    public static void main(String[] args) {
        Data1 data1 = new Data1();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data1.consur1();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data1.consur3();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data1.consur2();
            }
        }).start();
    }

}

class Data1 {
    private int num = 1;
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void consur1() {
        lock.lock();
        try {
            while (num != 1) {
                condition1.await();
            }
            System.out.println("AAAAAAAA");
            num = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consur2() {
        lock.lock();
        try {
            while (num != 2) {
                condition2.await();
            }
            System.out.println("BBBBBBBB");
            num = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consur3() {
        lock.lock();
        try {
            while (num != 3) {
                condition3.await();
            }
            System.out.println("CCCCCCCC");
            num = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
