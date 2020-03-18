package com.lafer.studythread;

public class MyThreadYieldMain {
    public static void main(String[] args) {
        MyThreadYield myThreadYield1 = new MyThreadYield();
        MyThreadYield myThreadYield2 = new MyThreadYield();

        myThreadYield1.setName("Y1");
        myThreadYield2.setName("Y2");

        myThreadYield1.start();
        myThreadYield2.start();


    }
}
