package ru.otus.aop.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.aop.proxy.annotations.Log;

public class ProxyFactory {
    private static final Logger logger = LoggerFactory.getLogger(ProxyFactory.class);

    private ProxyFactory() {}

     public static <T> ProxyCalcI createCalcLogger(Class<T> type) {
        InvocationHandler handler = new HandlerLogging<>(type);
        return (ProxyCalcI)
                Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(), type.getInterfaces(), handler);
    }

     static class HandlerLogging<T> implements InvocationHandler {
        private final T dependency;
        private final List<Method> logs = new ArrayList<>();

        HandlerLogging(Class<T> type) {
            this.dependency = instantiate(type);

            for (Method method : type.getDeclaredMethods()) {
                for (Annotation annotation : method.getAnnotations()) {
                    if (annotation instanceof Log) {
                        logs.add(method);
                    }
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (logs.stream().anyMatch(a ->
                    a.getName().equals(method.getName())
                    && a.getReturnType().equals(method.getReturnType())
                    && equalParamTypes(a.getParameterTypes(), method.getParameterTypes())
            )) {
                logger.info("executed method: {}, param {}", method, args);
            }

            return method.invoke(dependency, args);
        }

         private T instantiate(Class<T> type) {
             try {
                 return type.getDeclaredConstructor().newInstance();
             } catch (Exception e) {
                 throw new RuntimeException(e);
             }
         }

         private  boolean equalParamTypes(Class<?>[] params1, Class<?>[] params2) {
             if (params1.length == params2.length) {
                 for (int i = 0; i < params1.length; i++) {
                     if (params1[i] != params2[i])
                         return false;
                 }
                 return true;
             }
             return false;
         }
    }
}
