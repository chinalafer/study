package com.lafer.studythread;

public class MyThreadStopMain {

    public static void main(String[] args) {
        MyThreadStop myThreadStop = new MyThreadStop();

        Thread thread = new Thread(myThreadStop, "threadStop");
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myThreadStop.stop();

        Thread.yield();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
