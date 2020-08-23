package com.lafer.studythread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestConsumerAndProducer {

    public static void main(String[] args) {
        Resource resource = new Resource(10);
        Consumer consumer = new Consumer(resource);
        Producer producer = new Producer(resource);
        new Thread(consumer, "消费者1").start();
        new Thread(consumer, "消费者2").start();
        new Thread(producer, "生产者1").start();
        new Thread(producer, "生产者2").start();
    }



}

class Resource {

    private int maxSize;

    private Queue<Integer> collection;

    public Resource(int maxSize) {
        this.maxSize = maxSize;
        collection = new LinkedList<Integer>();
    }

    public synchronized void comsumer() {
        //资源满，消费者等待
        while (collection.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //消费
        System.out.println(Thread.currentThread().getName() + " 消费" + collection.poll() + "号商品");
        //通知生产者生产
        this.notifyAll();

    }

    public synchronized void producer() {
        //资源满，生产者等待
        while (collection.size() == maxSize) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Random random = new Random();
        int code = random.nextInt(1000);
        collection.add(code);
        //生产
        System.out.println(Thread.currentThread().getName() + "生产" + code + "号商品");
        //通知消费者生产
        this.notifyAll();
    }

}

class Consumer implements Runnable {

    private Resource resource;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            resource.comsumer();
        }
    }
}

class Producer implements Runnable {

    private Resource resource;

    public Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            resource.producer();
        }
    }
}

