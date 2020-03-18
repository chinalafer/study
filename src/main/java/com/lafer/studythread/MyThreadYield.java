package com.lafer.studythread;

public class MyThreadYield extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Thread.yield();
            System.out.println(getName() + ":" + i);
        }
    }
}
