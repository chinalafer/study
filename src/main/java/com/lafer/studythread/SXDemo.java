package com.lafer.studythread;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class SXDemo {

    public static void main(String[] args) {
        List<String> collection = new ArrayList<>();
        SCZ scz = new SCZ(collection);
        XFZ xfz = new XFZ(collection);
        Thread thread = new Thread(scz);
        Thread thread1 = new Thread(xfz);
        thread.start();
        thread1.start();
    }

}

class SCZ implements Runnable {

    List<String> collection;

    public SCZ(List<String> collection) {
        this.collection = collection;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (collection) {
                System.out.println(collection.size());
                while (collection.size() == 10) {
                    try {
                        collection.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if ((collection.size() & 1) == 0) {
                    collection.add("一号机器人");
                    System.out.println( "生产者生产一号机器人");
                } else {
                    collection.add("二号机器人");
                    System.out.println("生产者生产一号机器人");
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                collection.notify();
            }
        }
    }
}

class XFZ implements Runnable {

    List<String> collection;

    public XFZ(List<String> collection) {
        this.collection = collection;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (collection) {
                while (collection.size() == 0) {
                    try {
                        collection.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("消费者消费" + collection.remove(0));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                collection.notify();
            }
        }
    }
}
