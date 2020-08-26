package com.lafer.studythread;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionInterfaceTest {

    public static void main(String[] args) {
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
        consumerTest();
        predicateTest();
        supplierTest();
    }

    private static void consumerTest() {
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

    private static void predicateTest() {
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

    private static void supplierTest() {
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
}
