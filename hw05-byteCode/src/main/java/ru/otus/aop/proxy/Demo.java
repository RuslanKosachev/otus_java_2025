package ru.otus.aop.proxy;

public class Demo {
    public static void main(String[] args) {
        TestLoggingInterface myClass = IocProxy.createTestLogging();
        myClass.calculation(7);
        myClass.calculation(7, 9);
        myClass.calculation(7, 9, 11);
    }
}
