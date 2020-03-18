package com.lafer.studythread;

public class AnonymousDemo {

    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 100; i++) {
//                    System.out.println(Thread.currentThread().getName() + " : " + i);
//                }
//            }
//        }).start();

        new Thread(() -> {
            for (int i = 10001; i < 11111; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }) {
            @Override
            public void run() {
                System.out.println("asdf");
            }
        }.start();

    }

}
