package com.lafer.studythread;

public class MyThreadJoinMain {

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        MyThread myThread3 = new MyThread();


        myThread1.setName("lafer");
        myThread2.setName("jiaojiao");
        myThread3.setName("kk");

        myThread1.start();

        try {
            myThread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myThread2.start();
        myThread3.start();

    }

}
