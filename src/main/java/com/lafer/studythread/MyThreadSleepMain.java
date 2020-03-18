package com.lafer.studythread;

public class MyThreadSleepMain {
    public static void main(String[] args) {
        MyThreadSleep myThreadSleep1 = new MyThreadSleep();
        MyThreadSleep myThreadSleep2 = new MyThreadSleep();

        myThreadSleep1.setPriority(10);

        myThreadSleep1.setName("线程1");
        myThreadSleep2.setName("线程2");

        myThreadSleep1.start();
        myThreadSleep2.start();

    }
}
