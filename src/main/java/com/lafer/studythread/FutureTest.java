package com.lafer.studythread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + " -> " + "runAsync");
//        });
//        System.out.println(Thread.currentThread().getName());
//        completableFuture.get();
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " -> " + "supplyAsync");
            int a = 1 / 0;
            return "hello world ";
        });
        System.out.println(stringCompletableFuture.whenCompleteAsync((a, b) -> {
            System.out.println(a);
            System.out.println(b);
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return "500";
        }).get());
    }
}
