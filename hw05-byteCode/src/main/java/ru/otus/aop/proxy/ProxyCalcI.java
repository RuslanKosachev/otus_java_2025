package ru.otus.aop.proxy;

public interface ProxyCalcI {
    void calculation(int param);

    void calculation(int paramX, int paramY);

    void calculation(int paramX, int paramY, int paramZ);

    void calculation(int paramX, int paramY, long paramZ);
}
