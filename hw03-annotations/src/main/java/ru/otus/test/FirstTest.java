package ru.otus.test;

import ru.otus.framework.test.annotations.After;
import ru.otus.framework.test.annotations.Before;
import ru.otus.framework.test.annotations.Test;

public class FirstTest {

    @Before
    public void init() {
        System.out.println("init");
    }

    @Test
    public void test1() {
        System.out.println("test1 assert");
    }

    @Test
    public void test2() {
        throw new RuntimeException("test2 error");
    }

    @Test
    public void test3() {
        System.out.println("test3 assert");
    }

    @After
    public void end() {
        throw new RuntimeException("end");
    }
}
