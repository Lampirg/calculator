package com.lampirg.calculator.logic;

import java.util.function.Function;

public interface Calculator<T, R> extends Function<T, R> {
    R calculate(T expression);

    @Override
    default R apply(T t) {
        return calculate(t);
    }
}
