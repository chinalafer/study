package com.lafer.studythread;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest {
    public static void main(String[] args) throws Exception {
//        test1();
//        test2();
//        test3();
        test4();
    }

    private static void test1() {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.element());
        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.add(3);
//        blockingQueue.add(4);
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
    }

    private static void test2() {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(2));
        System.out.println(blockingQueue.offer(3));
        System.out.println(blockingQueue.offer(4));
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }


    private static void test3() throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put(1);
        blockingQueue.put(2);
        blockingQueue.put(3);
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
    }


    private static void test4() throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer(4, 1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer(1, 1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer(2, 1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer(3, 1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
    }
}
