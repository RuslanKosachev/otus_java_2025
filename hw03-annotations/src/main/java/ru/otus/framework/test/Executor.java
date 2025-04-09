package ru.otus.framework.test;

import ru.otus.framework.test.State;
import ru.otus.framework.test.annotations.After;
import ru.otus.framework.test.annotations.Before;
import ru.otus.framework.test.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


import static com.google.common.collect.Iterables.isEmpty;

public class Executor {

    public static <T> void run(Class<T> test)  {
        State<T> state = init(test);
        exec(state).printInfo();
    }

    private static <T> State<T> init(Class<T> test) {
        var state = new State<>(test);

        for (Method method : test.getDeclaredMethods()) {
            Annotation[] annotations = method.getAnnotations();
            if (annotations.length > 0) {
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Before) {
                        state.setBefore(method);
                    } else if (annotation instanceof Test) {
                        state.addUnits(method);
                    } else if (annotation instanceof After) {
                        state.setAfter(method);
                    }
                }
            }
        }
        if (isEmpty(state.getUnits())) {
            throw new RuntimeException("no tests");
        }

        return state;
    }

    private static <T> State<T> exec(State<T> state) {
        for (Method unit : state.getUnits()) {
            var instant = instantiate(state.getTest());

            try {
                callMethod(instant, state.getBefore());
                callMethod(instant, unit);
                state.passed();
            } catch (RuntimeException e) {
                System.out.printf("error test: message: %s \n", e.getMessage());
            } finally {
                try {
                    callMethod(instant, state.getAfter());
                } catch (RuntimeException e) {
                    System.out.printf("error after: message: %s \n", e.getMessage());
                }
            }
        }

        return state;
    }

    public static Object callMethod(Object object, Method m) {
        try {
            m.setAccessible(true);
            return m.invoke(object);
        } catch (Exception e) {
            throw new RuntimeException(m.getName(), e);
        }
    }

    public static <T> T instantiate(Class<T> type) {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
