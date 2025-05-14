package ru.otus.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.aop.proxy.ProxyCalcI;
import ru.otus.aop.proxy.annotations.Log;

public class DemoCalcSecondImpl implements ProxyCalcI {
    private static final Logger logger = LoggerFactory.getLogger(DemoCalcSecondImpl.class);

    @Override
    public void calculation(int paramX) {
        logger.info("calculation... X [{}]", paramX);
    }

    @Override
    public void calculation(int paramX, int paramY) {
        logger.info("calculation... X [{}] Y [{}]", paramX, paramY);
    }

    @Log
    @Override
    public void calculation(int paramX, int paramY, int paramZ) {
        logger.info("calculation... X [{}] Y [{}] Z [{}]", paramX, paramY, paramZ);
    }

    @Override
    public void calculation(int paramX, int paramY, long paramZ) {
        logger.info("calculation... X [{}] Y [{}] Z_long [{}]", paramX, paramY, paramZ);
    }
}
