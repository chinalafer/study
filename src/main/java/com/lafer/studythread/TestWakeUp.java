package com.lafer.studythread;

public class TestWakeUp {

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }

}

class Data {
    private int num = 0;

    public void increment() throws InterruptedException {
        synchronized (this) {
            while (num == 1) {
                this.wait();
            }
            System.out.println(Thread.currentThread().getName() + " -> " + ++num);
            this.notifyAll();
        }
    }

    public void decrement() throws InterruptedException {
        synchronized (this) {
            while (num == 0) {
                this.wait();
            }
            System.out.println(Thread.currentThread().getName() + " -> " + --num);
            this.notifyAll();
        }
    }
}
