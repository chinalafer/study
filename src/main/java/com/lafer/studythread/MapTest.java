package com.lafer.studythread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class MapTest {

    public static void main(String[] args) {
        //  Map<String, String> map = new HashMap<>();
        //  Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int temp = 0; temp < 10; temp++) {
                    map.put(UUID.randomUUID().toString().substring(0, 32), UUID.randomUUID().toString().substring(0, 32));
                    System.out.println(map);
                }
            }).start();
        }
    }

}
