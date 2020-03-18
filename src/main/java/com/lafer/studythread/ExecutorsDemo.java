package com.lafer.studythread;

import java.util.concurrent.*;

public class ExecutorsDemo {

    public static void main(String[] args) {

        ExecutorService poll = Executors.newFixedThreadPool(2);

//        poll.submit(new MyRunnable());
//        poll.submit(new MyRunnable());

        Future<Integer> future = poll.submit(new MyCallable(0, 100));
        Future<Integer> future1 = poll.submit(new MyCallable(101, 500));


        try {
            Integer integer = future.get();
            Integer integer1 = future1.get();
            System.out.println(integer);
            System.out.println(integer1 + integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        poll.shutdown();

    }

}
