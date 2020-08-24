package com.lafer.studythread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) {
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new MyCallableThread());
        new Thread(integerFutureTask).start();
        new Thread(integerFutureTask).start();
        try {
            int ret = integerFutureTask.get();
            System.out.println(ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
class MyCallableThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("lafer");
        int ret = 0;
        for (int i = 0; i < 100; i++) {
            ret += i;
        }
        return ret;
    }
}
