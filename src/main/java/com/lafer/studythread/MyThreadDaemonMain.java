package com.lafer.studythread;

public class MyThreadDaemonMain {

    public static void main(String[] args) {

        MyThreadDaemon myThreadDaemon1 = new MyThreadDaemon();
        MyThreadDaemon myThreadDaemon2 = new MyThreadDaemon();

        myThreadDaemon1.setName("l1");
        myThreadDaemon2.setName("l2");
        Thread.currentThread().setName("main");

        myThreadDaemon1.setDaemon(true);
        myThreadDaemon2.setDaemon(true);

        myThreadDaemon1.start();
        myThreadDaemon2.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

}
