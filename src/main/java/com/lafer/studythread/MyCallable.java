package com.lafer.studythread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

    private int start;
    private int end;

    public MyCallable(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int result = 0;
        for (int i = start; i <= end; i++) {
            result += i;
        }
        return result;
    }
}
