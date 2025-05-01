package ru.otus.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.aop.proxy.annotations.Log;

class IocProxy {
    private static final Logger logger = LoggerFactory.getLogger(IocProxy.class);

    private IocProxy() {}

    static TestLoggingInterface createTestLogging() {
        InvocationHandler handler = new TestLoggingHandler(new TestLoggingImpl());
        return (TestLoggingInterface)
                Proxy.newProxyInstance(IocProxy.class.getClassLoader(), new Class<?>[] {TestLoggingInterface.class}, handler);
    }

    static class TestLoggingHandler implements InvocationHandler {
        private final TestLoggingInterface dependency;

        TestLoggingHandler(TestLoggingInterface dependency) {
            this.dependency = dependency;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (Stream.of(method.getAnnotations()).anyMatch(a -> a instanceof Log)) {
                logger.info("executed method: {}, param {}", method, args);
            }
            return method.invoke(dependency, args);
        }
    }
}
