package ru.otus.test;

import ru.otus.framework.test.annotations.After;
import ru.otus.framework.test.annotations.Before;
import ru.otus.framework.test.annotations.Test;

public class SecondTest {

    @Before
    public void init() {
        System.out.println("init");
    }

    @Test
    public void test1() {
        throw new RuntimeException("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2 assert");
    }

    @Test
    public void test3() {
        System.out.println("test3 assert");
    }

    @Test
    public void test4() {
        throw new RuntimeException("test4");
    }

    @Test
    public void test5() {
        System.out.println("test5 assert");
    }

    @After
    public void end() {
        System.out.println("end");
    }
}
