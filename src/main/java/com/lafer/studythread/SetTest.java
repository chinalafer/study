package com.lafer.studythread;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetTest {

    public static void main(String[] args) {
        //  set<String> list = new HashSet<>();
        //  Set<String> list = Collections.synchronizedSet(new HashSet<>());
        Set<String> list = new CopyOnWriteArraySet<>();
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
