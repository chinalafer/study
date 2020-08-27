package com.lafer.studythread;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.Lock;
import java.util.stream.LongStream;

public class ForkJoinTest {

    //sum= 500000000500000000 time = 6558
    public static void test1() {
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (Long i = 1L; i <= 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum= " + sum + " time = " + (end - start));
    }

    //sum= 500000000500000000 time = 4160
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask1 task = new ForkJoinTask1(1L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum= " + sum + " time = " + (end - start));
    }

    //sum= 500000000500000000 time = 195
    public static void test3() {
        long start = System.currentTimeMillis();
        Long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum= " + sum + " time = " + (end - start));
    }
}

/**
 * 求和计算任务：
 *
 * 如何使用ForkJoin
 * 1、forkjionpool 来执行
 * 2、计算任务 forkjoinPool.execute(ForkJoinTask task)
 * 3、计算类要继承 ForkJoinTask
 */

class ForkJoinTask1 extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    private Long LJZ = 10000L;
    public ForkJoinTask1(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start < LJZ) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            Long middle = start + (end - start) / 2;
            // 拆分任务， 把任务压入线程队列
            ForkJoinTask1 task1 = new ForkJoinTask1(start, middle);
            ForkJoinTask1 task2 = new ForkJoinTask1(middle + 1, end);
            task1.fork();
            task2.fork();
            // 合并结果
            return task1.join() + task2.join();
        }
    }
}