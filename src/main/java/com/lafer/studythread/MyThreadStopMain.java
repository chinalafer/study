package com.lafer.studythread;

public class MyThreadStopMain {

    public static void main(String[] args) {
        MyThreadStop myThreadStop = new MyThreadStop();

        myThreadStop.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myThreadStop.interrupt();

    }

}
