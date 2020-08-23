### JUC

#### 线程与进程

> 概念

进程：是程序的一次执行过程，是系统资源分配的单位

线程：进程可以包含多个线程，也必须包含一个线程，线程是CPU调度和执行的基本单位

**Java不可以真的开启线程**

new Thread().start 会调用native方法，底层是用C++开启线程的。

> 并发与并行

并发：同一时间内运行多个线程

并行：同一时间点运行多个线程

并发编程的本质是：==充分利用CPU资源==

> 线程状态

```java
public enum State {
    // 新生
    NEW,
	// 运行
    RUNNABLE,
	// 阻塞
    BLOCKED,
	// 等待
    WAITING,
	// 超时等待
    TIMED_WAITING,
	//终止
    TERMINATED;
}
```

>wait/sleep区别

- wait在多线程通信中使用，使用之前需要先获得对象的锁，调用wait之后会释放锁，被其他线程调用notify唤醒之后进入就绪状态接着执行。wait是一个object方法。
- sleep使当前线程休眠指定时间之后，进入就绪状态。休眠过程中，不会释放锁，Thrad类方法。

#### Lock锁

官方API推荐用法

```java
Lock l = ...; 
l.lock;
try {
    //访问被锁保护的资源
} finally {
    l.unlock();
}
```

已知实现类：

ReentrantLock(可重入锁) ， ReentrantReadWriteLock.ReadLock （读锁）， ReentrantReadWriteLock.WriteLock （写锁）

初识公平锁与非公平锁

```java
public ReentrantLock() {
    sync = new NonfairSync();
}
//默认使用非公平锁
public ReentrantLock(boolean fair) {
    sync = fair ? new FairSync() : new NonfairSync();
}
```

> Synchronized 和 Lock 区别

1. Synchronized	内置的Java关键字；Lock	是一个Java类
2. Synchronized    无法判断获取锁的状态；Lock    可以判断是否获取到了锁
3. Synchronized    会自动释放锁（隐式锁）；Lock    必须手动释放锁
4. Synchronized    线程1（获得锁，阻塞）、线程2（一直等待）；Lock锁    不一定会等待下去（tryLock）
5. Synchronized    可重入锁，不可中断，非公平；Lock    可重入锁，非公平（可以自己设置）
6. Synchronized    适合锁少量的代码同步问题；Lock    适合锁大量的同步代码

#### 生产者消费者的实现

1. synchronized + wait + notify
2. lock + condition + await + signal

Condition可以实现精准通知唤醒

```java
public class TestLockCondition {

    public static void main(String[] args) {
        Data1 data1 = new Data1();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data1.consur1();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data1.consur3();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data1.consur2();
            }
        }).start();
    }

}

class Data1 {
    private int num = 1;
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void consur1() {
        lock.lock();
        try {
            while (num != 1) {
                condition1.await();
            }
            System.out.println("AAAAAAAA");
            num = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consur2() {
        lock.lock();
        try {
            while (num != 2) {
                condition2.await();
            }
            System.out.println("BBBBBBBB");
            num = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consur3() {
        lock.lock();
        try {
            while (num != 3) {
                condition3.await();
            }
            System.out.println("CCCCCCCC");
            num = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
```

