package com.lafer.study;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Test {
    public static void main(String[] args) {
        Resource resource = new Resource();
        //生产线程
        Producer p1 = new Producer(resource);
        //消费线程
        Consumer c1 = new Consumer(resource);
        new Thread(p1).start();
        new Thread(c1).start();
    }

}

class Resource {
    //当前资源的数量
    private int num = 0;
    //当前资源的上限
    private int size = 10;
    private Lock lock = new ReentrantLock();//创建锁对象
    private Condition consumerCondition = lock.newCondition();
    private Condition producerCondition = lock.newCondition();

    //消费资源
    public void remove() {
        try {
            lock.lock();//开启锁
            //如果num为0，没有资源了，需要等待
            while (num == 0) {//这里jdk源码里推荐用while，因为有可能出现虚假唤醒，所以要再次确认
                try {
                    System.out.println("消费者进入等待");
                    consumerCondition.await();//线程等待，并释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //如果线程可以执行到这里，说明资源里有资源可以消费
            num--;
            System.out.println("消费者线程为:" + Thread.currentThread().getName() + "--资源数量:" + num);
            producerCondition.signal();//唤醒其他等待的线程
        } finally {
            lock.unlock();//释放锁
        }
    }

    //生产资源
    public void put() {
        try {
            lock.lock();//开启锁
            //如果资源满了，就进入阻塞状态
            while (num == size) {//这里jdk源码里推荐用while，因为有可能出现虚假唤醒，所以要再次确认
                try {
                    System.out.println("生产者进入等待");
                    producerCondition.await();//线程等待，并释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num++;//如果线程执行到这里，说明资源未满，可以开始生产
            System.out.println("生产者线程为:" + Thread.currentThread().getName() + "--资源数量:" + num);
            consumerCondition.signal();//唤醒其他等待的线程
        } finally {
            lock.unlock();//释放锁
        }
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
            resource.remove();
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
            resource.put();
        }
    }
}