package com.lafer.studythread;

import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        System.out.println(atomicInteger.compareAndSet(1, 2));
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(1, 2));
        System.out.println(atomicInteger.get());
    }
}
