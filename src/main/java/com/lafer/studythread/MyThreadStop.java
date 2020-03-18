package com.lafer.studythread;

public class MyThreadStop extends Thread {

    @Override
    public void run() {
        System.out.println("sleep start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Thread Stop");
        }
        System.out.println("sleep end");
    }
}
