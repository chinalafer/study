package com.lafer.studyjvm;

public class ClassLoadTest {
    public static void main(String[] args) {
        ClassLoadTest classLoadTest1 = new ClassLoadTest();
        ClassLoadTest classLoadTest2 = new ClassLoadTest();
        ClassLoadTest classLoadTest3 = new ClassLoadTest();
        System.out.println(classLoadTest1.hashCode());
        System.out.println(classLoadTest2.hashCode());
        System.out.println(classLoadTest3.hashCode());
        System.out.println(classLoadTest1.getClass());
        System.out.println(classLoadTest2.getClass());
        System.out.println(classLoadTest3.getClass());
        System.out.println(classLoadTest1.getClass().getClassLoader());
        System.out.println(classLoadTest1.getClass().getClassLoader().getParent());
        System.out.println(classLoadTest1.getClass().getClassLoader().getParent().getParent());
    }
}
