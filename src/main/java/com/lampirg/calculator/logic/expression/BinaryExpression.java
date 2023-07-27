package com.lampirg.calculator.logic.expression;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BiFunction;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class BinaryExpression implements DoubleExpression {
    private final Expression<Double> x1;
    private final Expression<Double> x2;
    private final BiFunction<Double, Double, Double> operation;

    @Override
    public Double compute() {
        return operation.apply(x1.compute(), x2.compute());
    }
}
