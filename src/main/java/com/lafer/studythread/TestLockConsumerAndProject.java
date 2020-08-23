package com.lafer.studythread;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockConsumerAndProject {

    public static void main(String[] args) {
        Collection collection = new Collection(10);
        Consu consu = new Consu(collection);
        Prod prod = new Prod(collection);
        new Thread(consu).start();
        new Thread(prod).start();
    }

}

class Collection {
    private int size;
    private Queue<Integer> queue= new LinkedList<Integer>();
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public Collection(int size) {
        this.size = size;
    }

    public void consumer() {
        try {
            lock.lock();
            while (queue.size() == 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "消费" + queue.poll() + "号商品");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void producer() {
        try{
            lock.lock();
            while (queue.size() == size) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Random random = new Random();
            int i = random.nextInt(1000);
            System.out.println(Thread.currentThread().getName() + "生产" + i + "号商品");
            queue.add(i);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}

class Consu implements Runnable{

    private Collection collection;

    public Consu(Collection collection) {
        this.collection = collection;
    }

    @Override
    public void run() {
        while (true) {
            collection.consumer();
        }
    }
}

class Prod implements Runnable{

    private Collection collection;

    public Prod(Collection collection) {
        this.collection = collection;
    }

    @Override
    public void run() {
        while (true) {
            collection.producer();
        }
    }
}
