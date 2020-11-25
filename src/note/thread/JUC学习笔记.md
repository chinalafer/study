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
- sleep使当前线程休眠指定时间之后，进入就绪状态。休眠过程中，不会释放锁，Thread类方法。

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

####ReadWriteLock

```java
/**
 * 独占锁（写锁） 一次只能有一个线程占有
 * 共享锁（读锁） 多个线程可以同时占有
 * ReadWriteLock 
 * 读-读 可以共存
 * 读-写 不可以共存
 * 写-写 不可以共存
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 10; i++) {
            if ((i & 1) == 0) {
                int finalI = i;
                new Thread(() -> {
                    myCache.put(String.valueOf(finalI), finalI);
                }, String.valueOf(i)).start();
            } else {
                int finalI = i;
                new Thread(() -> {
                    myCache.get(String.valueOf(finalI));
                }, String.valueOf(i)).start();
            }
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    // 读写锁，更细粒度的锁
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    // 写入的时候，只能有一个线程写
    public void put(String key, Object value) {
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入开始");
            map.put(key, value);
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "写入完毕");
            reentrantReadWriteLock.writeLock().unlock();
        }
    }
    // 读取的时候，所有线程都可以读
    public void get(String key) {
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取开始");
            map.get(key);
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "读取完毕");
            reentrantReadWriteLock.readLock().unlock();
        }
    }
}
```

#### 阻塞队列

![image-20200825212657947](https://gitee.com/lafer/laferImage/raw/master/img/image-20200825212657947.png)

**四组API**

| 方式       | 抛出异常 | 有返回值 | 阻塞等待 | 超时间等待 |
| ---------- | -------- | -------- | -------- | ---------- |
| 添加       | add      | offer()  | put      | offer(,,)  |
| 移除       | remove   | poll()   | take     | poll(,)    |
| 判断队列首 | element  | peek()   |          |            |

>SynchronousQueue

```java
/**
 * 同步队列
 * 和其他的BlockingQueue 不一样，SynchronousQueue 不存储元素
 * put了一个元素，必须从里面take出来，否则不能再put进去。
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        // 同步队列
        BlockingQueue<String> queue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                queue.put("1");
                System.out.println(Thread.currentThread().getName() + " put 2");
                queue.put("2");
                System.out.println(Thread.currentThread().getName() + " put 3");
                queue.put("3");
                System.out.println(Thread.currentThread().getName() + " put 4");
                queue.put("4");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread1").start();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " take " + queue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " take "  + queue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " take " + queue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " take " + queue.take());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "thread2").start();
    }
}
```

#### 线程池

一些资源和连接的创建和销毁是十分占用资源的。

池化技术：事先准备好一些资源，以供使用。

**线程池的好处**

1、降低资源的消耗

2、提高响应的速度

3、方便管理

==线程复用、可以控制最大并发数，管理线程==

> 线程池：三大方法

![image-20200825222225417](https://gitee.com/lafer/laferImage/raw/master/img/image-20200825222225417.png)

```java
// Executors 工具类 3大方法
public class ExecutorsTest {
    public static void main(String[] args) {
        // 创建单个线程
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 创建指定大小线程池
        // ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 创建具有伸缩性质的线程池
        // ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 100; i++) {
                // 使用了线程池之后，使用线程池来创建线程
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " running");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池使用完，程序结束，关闭线程池
            threadPool.shutdown();
        }
    }
}
```

> 7大参数

```java
public static ExecutorService newSingleThreadExecutor() {
    return new FinalizableDelegatedExecutorService
        (new ThreadPoolExecutor(1, 1,
                                0L, TimeUnit.MILLISECONDS,
                                new LinkedBlockingQueue<Runnable>()));
}    
public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(nThreads, nThreads,
                                  0L, TimeUnit.MILLISECONDS,
                                  new LinkedBlockingQueue<Runnable>());
}
public static ExecutorService newCachedThreadPool() {
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                  60L, TimeUnit.SECONDS,
                                  new SynchronousQueue<Runnable>());
}
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue) {
    this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
         Executors.defaultThreadFactory(), defaultHandler);
}
public ThreadPoolExecutor(int corePoolSize,			// 核心线程数
                          int maximumPoolSize,		// 最大线程数
                          long keepAliveTime,		// 当线程数大于核心线程数时，多余的空闲线程存活的最长时间
                          TimeUnit unit,			// 单位
                          BlockingQueue<Runnable> workQueue,// 等待队列
                          ThreadFactory threadFactory,		// 创建线程工程
                          RejectedExecutionHandler handler	// 需要执行的任务大于最大线程数加上等待队列的大小之后，后面的任务采取 拒绝策略
                         ) {
    if (corePoolSize < 0 ||
        maximumPoolSize <= 0 ||
        maximumPoolSize < corePoolSize ||
        keepAliveTime < 0)
        throw new IllegalArgumentException();
    if (workQueue == null || threadFactory == null || handler == null)
        throw new NullPointerException();
    this.corePoolSize = corePoolSize;
    this.maximumPoolSize = maximumPoolSize;
    this.workQueue = workQueue;
    this.keepAliveTime = unit.toNanos(keepAliveTime);
    this.threadFactory = threadFactory;
    this.handler = handler;
}
```

```java
/**
 * 拒绝策略
 * new ThreadPoolExecutor.AbortPolicy()           队列满了，不执行，抛出异常
 * new ThreadPoolExecutor.CallerRunsPolicy()      队列满了，退回给原有的线程执行
 * new ThreadPoolExecutor.DiscardPolicy()         队列满了，丢掉任务，不抛出异常
 * new ThreadPoolExecutor.DiscardOldestPolicy()   队列满了，尝试去和最早的任务竞争，也不会抛出异常
 */
public class ExecutorsTest {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(3,
                5,
                3L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
                );
        try {
            for (int i = 1; i <= 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " running");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池使用完，程序结束，关闭线程池
            threadPool.shutdown();
        }
    }
}
```

> 注意点

```java
// 如何定义最大线程数
// 1、CUP密集型     cpu是几核的就是几， 可以保持CPU的效率最高
// 获取CPU的核数
System.out.println(Runtime.getRuntime().availableProcessors());
// 2、IO密集型      判断程序中十分耗IO的线程，大于这个数就可以。
```

#### 四大函数式接口

> Function

```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}

public void functionTest() {
    Function<String, String> function = new Function<String, String>() {
        @Override
        public String apply(String s) {
            return "Hello " + s;
        }
    };
    System.out.println(function.apply("la"));
    Function<String, String> function1 = (s) -> {
        return "Hello " + s;
    };
    System.out.println(function1.apply("lafer"));
}
```
>Predicate

```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}

private void predicateTest() {
    Predicate<String> predicate = new Predicate<String>() {
        @Override
        public boolean test(String o) {
            return "lafer".equals(o);
        }
    };
    System.out.println(predicate.test("lafer"));
    Predicate<String> predicate1 = (s) -> {
        return "lafer".equals(0);
    };
    System.out.println(predicate1.test("123"));
}
```
>Consumer

```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}

private void consumerTest() {
    Consumer<String> consumer = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println("consumer " + s);
        }
    };
    consumer.accept("lafer");
    Consumer<String> consumer1 = (s) -> {
        System.out.println("consumer " + s);
    };
    consumer1.accept("lafer");
}
```
>Supplier

```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}

private void supplierTest() {
    Supplier<String> supplier = new Supplier<String>() {
        @Override
        public String get() {
            return "lafer";
        }
    };
    System.out.println(supplier.get());
    Supplier<String> supplier1 = () -> {return "lafer";};
    System.out.println(supplier1.get());
}
```

#### Stream流式计算

```java
/**
 * 1、ID必须是偶数
 * 2、年龄必须大于23岁
 * 3、用户名转为大写字母
 * 4、只输出一个用户
 */

public class StreamTest {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 21);
        User u2 = new User(2, "b", 22);
        User u3 = new User(3, "c", 23);
        User u4 = new User(4, "d", 24);
        User u5 = new User(6, "e", 25);
        List<User> users = Arrays.asList(u1, u2, u3, u4, u5);
        users.stream()
                .filter(user -> {return (user.getId() % 2) == 0;})
                .filter(user -> {return (user.getAge() > 23);})
                .map((user -> {return user.getName().toUpperCase();}))
                .sorted((name1, name2) -> {return name2.compareTo(name1);})
                .limit(1)
                .forEach(System.out::println);
    }
}
```

#### ForkJoin

![img](https://gitee.com/lafer/laferImage/raw/master/img/42a98226cffc1e17c9adfa158ef74106738de975.jpeg)

从JDK1.7开始，Java提供ForkJoin框架用于并行执行任务，它的思想就是讲一个大任务分割成若干小任务，最终汇总每个小任务的结果得到这个大任务的结果。

> ForkJoinPool

使用ForkJoinPool来执行被细分之后的任务。

ForkJoinPool与其它的ExecutorService区别主要在于它使用“工作窃取“，

**工作窃取**

一个大任务会被划分成无数个小任务，这些任务被分配到不同的队列，这些队列有些干活干的块，有些干得慢。于是干得快的，一看自己没任务需要执行了，就去隔壁的队列里面拿去任务执行。

>ForkJoinTask

ForkJoinTask就是ForkJoinPool里面的每一个任务。他主要有两个子类：RecursiveAction（一个递归无结果的ForkJoinTask）和RecursiveTask（一个递归有结果的ForkJoinTask）。然后通过fork()方法去分配任务执行任务，通过join()方法汇总任务结果。

```java
public class ForkJoinTest {

    //sum= 500000000500000000 time = 6558
    public static void test1() {
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (Long i = 1L; i <= 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum= " + sum + " time = " + (end - start));
    }

    //sum= 500000000500000000 time = 4160
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask1 task = new ForkJoinTask1(1L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);
        Long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum= " + sum + " time = " + (end - start));
    }

    //sum= 500000000500000000 time = 195
    public static void test3() {
        long start = System.currentTimeMillis();
        Long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum= " + sum + " time = " + (end - start));
    }
}

/**
 * 求和计算任务：
 *
 * 如何使用ForkJoin
 * 1、forkjionpool 来执行
 * 2、计算任务 forkjoinPool.execute(ForkJoinTask task)
 * 3、计算类要继承 ForkJoinTask
 */

class ForkJoinTask1 extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    private Long LJZ = 10000L;
    public ForkJoinTask1(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start < LJZ) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            Long middle = start + (end - start) / 2;
            // 拆分任务， 把任务压入线程队列
            ForkJoinTask1 task1 = new ForkJoinTask1(start, middle);
            ForkJoinTask1 task2 = new ForkJoinTask1(middle + 1, end);
            task1.fork();
            task2.fork();
            // 合并结果
            return task1.join() + task2.join();
        }
    }
}
```

#### 异步回调

```java
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + " -> " + "runAsync");
//        });
//        System.out.println(Thread.currentThread().getName());
//        completableFuture.get();
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " -> " + "supplyAsync");
            int a = 1 / 0;
            return "hello world ";
        });
        System.out.println(stringCompletableFuture.whenCompleteAsync((a, b) -> {
            System.out.println(a);
            System.out.println(b);
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return "500";
        }).get());
    }
}
```

#### JMM（Java Memory Model）

Volatile是Java虚拟机提供的轻量级同步机制

1. 保证可见性
2. 不保证原子性
3. 禁止指令重排

> 什么是JMM

JMM：Java内存模型（一个概念、约定）

**关于JMM的一些同步约定：**

1、线程加锁前，必须读取主存中的最新之到工作内存中

2、线程解锁前，必须把共享变量立刻刷回主存

3、加锁和解锁是同一把锁

![1102674-20180815143324915-2024156794](https://gitee.com/lafer/laferImage/raw/master/img/1102674-20180815143324915-2024156794.png)

> 内存交互操作

 　**内存交互操作有8种，虚拟机实现必须保证每一个操作都是原子的，不可在分的（对于double和long类型的变量来说，load、store、read和write操作在某些平台上允许例外）**

  - lock(锁定)：作用于主内存，它把一个变量标记为一条线程独占状态；
  - read(读取)：作用于主内存，它把变量值从主内存传送到线程的工作内存中，以便随后的load动作使用；
  - load(载入)：作用于工作内存，它把read操作的值放入工作内存中的变量副本中；
  - use(使用)：作用于工作内存，它把工作内存中的值传递给执行引擎，每当虚拟机遇到一个需要使用这个变量的指令时候，将会执行这个动作；
  - assign(赋值)：作用于工作内存，它把从执行引擎获取的值赋值给工作内存中的变量，每当虚拟机遇到一个给变量赋值的指令时候，执行该操作；
  - store(存储)：作用于工作内存，它把工作内存中的一个变量传送给主内存中，以备随后的write操作使用；
  - write(写入)：作用于主内存，它把store传送值放到主内存中的变量中。
  - unlock(解锁)：作用于主内存，它将一个处于锁定状态的变量释放出来，释放后的变量才能够被其他线程锁定；

**JMM对这八种指令的使用，制定了如下规则：**

 - 不允许read和load、store和write操作之一单独出现。即使用了read必须load，使用了store必须write
  - 不允许线程丢弃他最近的assign操作，即工作变量的数据改变了之后，必须告知主存
  - 不允许一个线程将没有assign的数据从工作内存同步回主内存
  - 一个新的变量必须在主内存中诞生，不允许工作内存直接使用一个未被初始化的变量。就是对变量实施use、store操作之前，必须经过assign和load操作
  - 一个变量同一时间只有一个线程能对其进行lock。多次lock后，必须执行相同次数的unlock才能解锁
  - 如果对一个变量进行lock操作，会清空所有工作内存中此变量的值，在执行引擎使用这个变量前，必须重新load或assign操作初始化变量的值
  - 如果一个变量没有被lock，就不能对其进行unlock操作。也不能unlock一个被其他线程锁住的变量
  - 对一个变量进行unlock操作之前，必须把此变量同步回主内存

#### Volatile

> 1.可见性验证

```java
public class Test {
    private static volatile boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (flag) {

            }
        }).start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            flag = false;
        }).start();
    }
}
```

> 2.不保证原子性

```java
public class Test {
    private static volatile int num = 0;

    private static void add() {
        num++;
    }

    private static void test() {
        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 10000; i1++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        // 结果少于2000000
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
```

lock 和 synchronized可以解决这个问题， 使用原子类也可以。

```java
public class Test {
    private static AtomicInteger num = new AtomicInteger(0);

    private static void add() {
        num.getAndIncrement();
    }

    private static void test() {
        for (int i = 0; i < 2000; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 10000; i1++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
```

**指令重排**

源代码->编译器优化的重排->指令并行也可能重排->内存系统也会重排->执行

处理器在进行指令重排的时候，考虑：==数据之间的依赖性==

> 3.禁止指令重排

**volatile 可以避免指令重排**

volatile 会在指令的前面和后面分别加一层内存屏障，防止与上面和下面的指令进行指令重排。

#### 单例模式

> 饿汉式

```java
/**
 * 饿汉式
 *
 * 不管使不使用，都会创建这个对象。
 *
 */

public class Hungry {

    // 构造器私有
    private Hungry() {

    }

    private static final Hungry HUNGRY = new Hungry();

    public static Hungry getInstance() {
        return HUNGRY;
    }

}
```

> 懒汉式

```java
/**
 * 懒汉式
 */

public class Lazy {

    private Lazy() {
        System.out.println(Thread.currentThread().getName());
    }

    private static volatile Lazy LAZY;

    // 双重检测锁模式的 懒汉式单例 DCL懒汉式
    public static Lazy getInstance() {
        if (LAZY == null) {
            synchronized (Lazy.class) {
                if (LAZY == null) {
                    LAZY = new Lazy();
                    /**
                     * LAZY = new Lazy();并不是原子性操作
                     * 1、分配内存空间
                     * 2、执行构造方法，初始化对象
                     * 3、把这个对象指向这个空间
                     *
                     * 可能发生指令重排
                     * 如果A线程执行1、3，b线程判断对象不为null，但是此时对象还没有完成构造，所以需要给对象加上volatile关键字，防止指令重排。
                     */
                }
            }
        }
        return LAZY;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                getInstance();
            }).start();
        }
    }

}
```

**由于反射，懒汉式的单例模式也不安全，可以使用枚举，枚举不支持反射调用构造方法。**

#### 理解CAS

```java
public class CASTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        System.out.println(atomicInteger.compareAndSet(1, 2));
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(1, 2));
        System.out.println(atomicInteger.get());
    }
}

// 源码
public class AtomicInteger extends Number implements java.io.Serializable {
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long valueOffset;
    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                (AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }
    private volatile int value;
    public final boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }
    public final int getAndIncrement() {
        return unsafe.getAndAddInt(this, valueOffset, 1);
    }
}
public final class Unsafe {
    public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
    public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            var5 = this.getIntVolatile(var1, var2);
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
        return var5;
    }
}
```

**CAS（compareAndSet）**：比较工作内存中的值和主存中的值，如果这个值是期望的，那么执行操作，否则一直循环（自旋锁）。

缺点：

1. 循环耗时
2. 一次性只能保证一个共享变量的原子性
3. ABA问题

> CAS：ABA问题

描述：线程A在执行CAS(1, 2)之前，线程执行了CAS(1, 3); CAS(3, 1)。对于线程A而言，是不知道共享变量已经发生过修改了的。

```java
public class ABATest {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " " + atomicInteger.get());
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " " + atomicInteger.compareAndSet(0, 1));
                System.out.println(Thread.currentThread().getName() + " " + atomicInteger.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " " + atomicInteger.get());
            System.out.println(Thread.currentThread().getName() + " " + atomicInteger.compareAndSet(0, 2));
            System.out.println(Thread.currentThread().getName() + " " + atomicInteger.compareAndSet(2, 0));
            System.out.println(Thread.currentThread().getName() + " " + atomicInteger.get());
        }).start();
    }
}
```

解决方案：原子引用

#### 原子引用

```java
public class ABATest {
    //AtomicStampedReference 注意，如果泛型是一个整型包装类，注意对象引用问题，比较的是两个对象的引用，
    private static AtomicStampedReference<Integer> atomicInteger = new AtomicStampedReference(0, 1);
    public static void main(String[] args) {
        new Thread(() -> {
            int stamp = atomicInteger.getStamp();
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " " + stamp);
                System.out.println(Thread.currentThread().getName() + " " + atomicInteger.compareAndSet(0, 1, stamp, stamp + 1));
                System.out.println(Thread.currentThread().getName() + " " + atomicInteger.getStamp());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            int stamp = atomicInteger.getStamp();
            System.out.println(Thread.currentThread().getName() + " " + stamp);
            System.out.println(Thread.currentThread().getName() + " " + atomicInteger.compareAndSet(0, 2, stamp, stamp + 1));
            System.out.println(Thread.currentThread().getName() + " " + atomicInteger.compareAndSet(2, 1, stamp + 1, stamp + 2));
            System.out.println(Thread.currentThread().getName() + " " + atomicInteger.getStamp());
        }).start();
    }
}
```

![image-20200829232556528](https://gitee.com/lafer/laferImage/raw/master/img/image-20200829232556528.png)

#### 各种锁的理解

##### 1、公平锁

先来先得，必须排队

##### 2、非公平锁

可以插队

##### 3、可重入锁

什么是 “可重入”，可重入就是说某个线程已经获得某个锁，可以再次获取锁而不会出现死锁

##### 4、自旋锁

```java
public final int getAndAddInt(Object var1, long var2, int var4) {
    int var5;
    do {
        var5 = this.getIntVolatile(var1, var2);
    } while(
        !this.compareAndSwapInt(var1, var2, var5, var5 + var4));
    return var5;
}
```
```java
public class SpinLockTest {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        System.out.println(Thread.currentThread().getName() + " - myLock");
        while (atomicReference.compareAndSet(null, Thread.currentThread())) {

        }
    }

    public void myUnLock() {
        System.out.println(Thread.currentThread().getName() + " - myUnLock");
        atomicReference.compareAndSet(Thread.currentThread(), null);
    }

    public static void main(String[] args) {
        SpinLockTest spinLockTest = new SpinLockTest();
        new Thread(() -> {
            try {
                spinLockTest.myLock();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLockTest.myUnLock();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                spinLockTest.myLock();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLockTest.myUnLock();
            }
        }, "B").start();
    }
    
}
```

#### 死锁

死锁的排查

```java
public class DeadLock {

    Lock lockA = new ReentrantLock();

    Lock lockB = new ReentrantLock();

    public void getLockAB() {
        lockA.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " get lockA ");
            TimeUnit.SECONDS.sleep(2);
            lockB.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " get lockB ");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lockB.unlock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockA.unlock();
        }
    }

    public void getLockBA() {
        lockB.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " get lockA ");
            TimeUnit.SECONDS.sleep(2);
            lockA.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " get lockB ");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lockA.unlock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockB.unlock();
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(() -> {
            deadLock.getLockAB();
        }, "Thread1").start();
        new Thread(() -> {
            deadLock.getLockBA();
        }, "Thread2").start();
    }

}
```

> 解决问题

1.使用`jps -l`定位进程号

![微信截图_20200830000630](https://gitee.com/lafer/laferImage/raw/master/img/微信截图_20200830000630.png)

2.使用`jstack 进程号`找到死锁问题

![image-20200830000935710](https://gitee.com/lafer/laferImage/raw/master/img/image-20200830000935710.png)

排查问题：

1、日志

2、堆栈信息