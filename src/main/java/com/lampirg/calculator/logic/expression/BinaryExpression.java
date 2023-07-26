package com.lampirg.calculator.logic.expression;

import lombok.RequiredArgsConstructor;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class BinaryExpression implements Expression<Double> {
    private final Expression<Double> x1;
    private final Expression<Double> x2;
    protected final BiFunction<Double, Double, Double> operation;

    @Override
    public Double compute() {
        return operation.apply(x1.compute(), x2.compute());
    }
}
