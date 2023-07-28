package com.lampirg.calculator.logic.expression;

import java.util.function.Supplier;

public interface Expression<T> extends Supplier<T> {
    T compute();

    @Override
    default T get() {
        return compute();
    }
}
