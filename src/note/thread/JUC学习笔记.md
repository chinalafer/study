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

#### 集合类不安全

> List 不安全

```java
public static void main(String[] args) {
    //  不安全
    //  List<String> list = new ArrayList<>();
    //  使用Collections提供的安全List
    //  List<String> list = Collections.synchronizedList(new ArrayList<>());
    //  Vector安全
    //  List<String> list = new Vector<>();
    List<String> list = new CopyOnWriteArrayList<>();
    //  CopyOnWrite 写入时复制 COW 计算机程序设计领域的一种优化策略
    for (int i = 0; i < 10; i++) {
        new Thread(() -> {
            for (int temp = 0; temp < 10; temp++) {
                list.add(UUID.randomUUID().toString().substring(0, 32));
                System.out.println(list);
            }
        }).start();
    }
    /**
    写入时复制一个新的数组（数组长度+1），将新的元素放置在数组末尾，然后使用新数组替换原有数组
    public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }
    */
}
```

> set不安全

```java
public static void main(String[] args) {
    //  不安全
    //  set<String> set = new HashSet<>();
    //  使用Collections提供的安全Set
    //  Set<String> set = Collections.synchronizedSet(new HashSet<>());
    Set<String> set = new CopyOnWriteArraySet<>();
    for (int i = 0; i < 10; i++) {
        new Thread(() -> {
            for (int temp = 0; temp < 10; temp++) {
                set.add(UUID.randomUUID().toString().substring(0, 32));
                System.out.println(list);
            }
        }).start();
    }
}
```

```java
// Set 的底层就是 Map
public HashSet() {
    map = new HashMap<>();
}
// 是不可变的Object对象
public boolean add(E e) {
    return map.put(e, PRESENT)==null;
}
```

> map不安全

```java
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
```

#### Callable

```java
@FunctionalInterface
public interface Callable<V> {
    V call() throws Exception;
}
//返回结果并可能引发异常的任务。
```

```java
public class CallableTest {
    public static void main(String[] args) {
        // 适配器类
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new MyCallableThread());
        new Thread(integerFutureTask).start();
        // 结果会被缓存
        new Thread(integerFutureTask).start();
        try {
            // get 方法可能产生阻塞
            int ret = integerFutureTask.get();
            System.out.println(ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
class MyCallableThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("lafer");
        int ret = 0;
        for (int i = 0; i < 100; i++) {
            ret += i;
        }
        return ret;
    }
}
```

**细节：**

1. 有缓存
2. 结果可能需要等待，会阻塞

#### 常用辅助类

##### CountDownLatch

```java
// 计数器
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        // 总数是5
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                // 数量 -1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        // 等待计数器归零， 然后再向下执行
        countDownLatch.await();
        System.out.println("end");
    }
}
```

原理：

`countDownLatch.countDown();` //数量-1

`countDownLatch.await();`//等待计算器归零，然后再向下执行

每次有线程调用countDown()数量-1，假设计数器变为0，countDownLatch.await()就会被唤醒，继续执行！

##### CyclicBarrier

```java
public class CyclicBarrierTest {
    // 十名同学到齐了之后，输出十名同学到齐啦
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println("十名同学到齐啦。。。");
        });
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "报到...");
                try {
                    cyclicBarrier.await();  // 等待
                    System.out.println(Thread.currentThread().getName() + "跑了...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
```

##### Semaphore

```java
public class SemaphoreTest {
    // 指定资源个数,多个线程来获取资源
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    //获取资源
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "获取资源");
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //释放资源
                    System.out.println(Thread.currentThread().getName() + "释放资源");
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
```

**原理：**

`semaphore.acquire();`获得，如果已经满了，等待，等待被释放为止。

`semaphore.release();`释放，会释放当前的信号量+1，然后唤醒等待的线程。

作用：多个共享资源户次的使用！并发限流，控制最大的线程数。