package com.lafer.studythread;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {

    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
//        List<String> list = new Vector<>();
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int temp = 0; temp < 10; temp++) {
                    list.add(UUID.randomUUID().toString().substring(0, 32));
                    System.out.println(list);
                }
            }).start();
        }
    }

}
