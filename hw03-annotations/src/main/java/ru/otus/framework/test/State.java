package ru.otus.framework.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class State<T> {
    private final Class<T> test;
    private final List<Method> units = new ArrayList<>();
    private Method before;
    private Method after;
    private int passed = 0;
    private int all = 0;

    public State(Class<T> test) {
        this.test = test;
    }

    public Class<T> getTest() {
        return test;
    }

    public Method getBefore() {
        return before;
    }

    public void setBefore(Method before) {
        this.before = before;
    }

    public Method getAfter() {
        return after;
    }

    public void setAfter(Method after) {
        this.after = after;
    }

    public void addUnits(Method unit) {
        this.units.add(unit);
        this.all = this.units.size();
    }

    public List<Method> getUnits() {
        return units;
    }

    public void passed() {
        this.passed++;
    }

    public void printInfo() {
        System.out.printf("\t Test %s passed:%d of %d, failed:%d \n",
                test.getSimpleName(), passed, all, all - passed);
    }
}
