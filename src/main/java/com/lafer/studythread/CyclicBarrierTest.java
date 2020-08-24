package com.lafer.studythread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    // 十名同学到齐了之后，输出十名同学到齐啦
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println("十名同学到齐啦。。。");
        });
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "报到...");
                try {
                    cyclicBarrier.await();  // 等待
                    System.out.println(Thread.currentThread().getName() + "跑了...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}