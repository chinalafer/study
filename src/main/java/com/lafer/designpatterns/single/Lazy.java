package com.lafer.designpatterns.single;

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
