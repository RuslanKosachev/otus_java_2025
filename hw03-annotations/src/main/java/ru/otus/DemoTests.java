package ru.otus;

import ru.otus.framework.test.Executor;
import ru.otus.test.InitExceptionTest;
import ru.otus.test.FirstTest;
import ru.otus.test.SecondTest;

public class DemoTests {
    public static void main(String[] args) {
        Executor.run(InitExceptionTest.class);
        Executor.run(FirstTest.class);
        Executor.run(SecondTest.class);
    }
}
