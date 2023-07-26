package com.lampirg.calculator.logic.expression;

import lombok.RequiredArgsConstructor;

import java.util.function.BiFunction;
import java.util.function.Function;

@RequiredArgsConstructor
public class BinaryExpression {
    private final int x1;
    private final int x2;
    protected final BiFunction<Integer, Integer, Integer> operation;

    public int compute() {
        return operation.apply(x1, x2);
    }
}
