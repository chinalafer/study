package com.lafer.designpatterns.single;

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
