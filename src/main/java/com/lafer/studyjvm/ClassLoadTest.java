package com.lafer.studyjvm;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ClassLoadTest {
    public static void main(String[] args) throws IOException {
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

        URL url = new URL("http://www.baidu.com");
        InputStream inputStream = url.openStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String buffer = null;
        while ((buffer = bufferedReader.readLine()) != null) {
            System.out.println(buffer);
        }
        bufferedReader.close();
    }
}
