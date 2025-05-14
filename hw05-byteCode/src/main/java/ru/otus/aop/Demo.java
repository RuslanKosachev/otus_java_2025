package ru.otus.aop;

import ru.otus.aop.proxy.ProxyFactory;

public class Demo {
    public static void main(String[] args) {
        var demoCalcFirst = ProxyFactory.createCalcLogger(DemoCalcFirstImpl.class);
        demoCalcFirst.calculation(7);
        demoCalcFirst.calculation(7, 9);
        demoCalcFirst.calculation(7, 9, 11);
        demoCalcFirst.calculation(7, 9, Long.MAX_VALUE);

        var demoCalcSecond = ProxyFactory.createCalcLogger(DemoCalcSecondImpl.class);
        demoCalcSecond.calculation(8);
        demoCalcSecond.calculation(8, 10);
        demoCalcSecond.calculation(8, 10, 12);
        demoCalcSecond.calculation(8, 10, Long.MIN_VALUE);
    }
}
