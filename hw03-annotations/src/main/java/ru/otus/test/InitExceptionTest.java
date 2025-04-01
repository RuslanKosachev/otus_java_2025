package ru.otus.test;

import ru.otus.framework.test.annotations.After;
import ru.otus.framework.test.annotations.Before;
import ru.otus.framework.test.annotations.Test;

public class InitExceptionTest {

    @Before
    public void init() {
        throw new RuntimeException("init");
    }

    @Test
    public void test1() {
        System.out.println("test1 assert");
    }

    @Test
    public void test2() {
        System.out.println("test2 assert");
    }

    @After
    public void end() {
        System.out.println("end");
    }
}
