package ru.otus.aop.proxy;

import jdk.jfr.Description;
import ru.otus.aop.proxy.annotations.Log;

public interface TestLoggingInterface {

    @Description("Test")
    void calculation(int param);

    @Log
    void calculation(int paramX, int paramY);

    void calculation(int paramX, int paramY, int paramZ);
}
