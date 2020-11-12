package com.epam.composite.model;

import java.util.LinkedList;
import java.util.Objects;

public class Context {
    private final LinkedList<Integer> contextValues;

    public Context() {
        contextValues = new LinkedList<>();
    }

    public Integer pop() {
        return contextValues.pop();
    }

    public void push(Integer number) {
        contextValues.push(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Context context = (Context) o;
        return Objects.equals(contextValues, context.contextValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextValues);
    }
}
