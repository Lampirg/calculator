package com.lampirg.calculator.logic.expression;

import lombok.RequiredArgsConstructor;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class BinaryNumberExpression {
    private final double x1;
    private final double x2;
    protected final BiFunction<Double, Double, Double> operation;

    public double compute() {
        return operation.apply(x1, x2);
    }
}
