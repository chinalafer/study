package com.lafer.studythread;

public class MyRunnableMain {

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();

        Thread thread = new Thread(myRunnable, "lafer");
        new Thread(myRunnable, "test1").start();
        thread.start();



    }

}
