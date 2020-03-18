package com.lafer.studythread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SellTicket implements Runnable {

    public Object key = new Object();

    public int ticket = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
//            synchronized (key) {
//                if (ticket > 0) {
//                    System.out.println(Thread.currentThread().getName() + " 正在出售第" + ticket-- + "张票");
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    return;
//                }
//            }
            lock.lock();
            try {

                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + " 正在出售第" + ticket-- + "张票");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    return;
                }
            } finally {
                lock.unlock();
            }

        }

    }

}
