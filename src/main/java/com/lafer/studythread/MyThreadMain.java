package com.lafer.studythread;

public class MyThreadMain {
    public static void main(String[] args) {

        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();

        myThread1.setName("线程1");
        myThread2.setName("线程2");

        myThread1.start();
        myThread2.start();

    }
}
