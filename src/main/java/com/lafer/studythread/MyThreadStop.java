package com.lafer.studythread;

public class MyThreadStop implements Runnable {

//    @Override
//    public void run() {
//        System.out.println("sleep start");
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            System.out.println("Thread Stop");
//        }
//        System.out.println("sleep end");
//    }

    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            System.out.println("run..." + Thread.currentThread().getName());
        }
    }

    public void stop() {
        this.flag = false;
    }

}
