package com.lafer.studythread;

import java.util.concurrent.*;

/**
 * 拒绝策略
 * new ThreadPoolExecutor.AbortPolicy()           队列满了，不执行，抛出异常
 * new ThreadPoolExecutor.CallerRunsPolicy()      队列满了，退回给原有的线程执行
 * new ThreadPoolExecutor.DiscardPolicy()         队列满了，丢掉任务，不抛出异常
 * new ThreadPoolExecutor.DiscardOldestPolicy()   队列满了，尝试去和最早的任务竞争，也不会抛出异常
 */
public class ExecutorsTest {
    public static void main(String[] args) {
        // 自定义线程！ 工作 ThreadPoolExecutor

        // 如何定义最大线程数
        // 1、CUP密集型     cpu是几核的就是几， 可以保持CPU的效率最高
        // 获取CPU的核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        // 2、IO密集型      判断程序中十分耗IO的线程，大于这个数就可以。

        ExecutorService threadPool = new ThreadPoolExecutor(3,
                5,
                3L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
                );
        try {
            for (int i = 1; i <= 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " running");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池使用完，程序结束，关闭线程池
            threadPool.shutdown();
        }
    }
}
