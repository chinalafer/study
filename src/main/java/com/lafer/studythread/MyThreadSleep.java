package com.lafer.studythread;

import java.util.Date;

public class MyThreadSleep extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("sleep before   " + new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sleep end   " + new Date());
        }
    }
}
