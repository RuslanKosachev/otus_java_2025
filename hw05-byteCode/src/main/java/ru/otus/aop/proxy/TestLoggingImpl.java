package ru.otus.aop.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLoggingImpl implements TestLoggingInterface {
    private static final Logger logger = LoggerFactory.getLogger(TestLoggingImpl.class);

    @Override
    public void calculation(int paramX) {
        logger.info("calculation... X [{}]", paramX);
    }

    @Override
    public void calculation(int paramX, int paramY) {
        logger.info("calculation... X [{}] Y [{}]", paramX, paramY);
    }

    @Override
    public void calculation(int paramX, int paramY, int paramZ) {
        logger.info("calculation... X [{}] Y [{}] Z [{}]", paramX, paramY, paramZ);
    }
}
