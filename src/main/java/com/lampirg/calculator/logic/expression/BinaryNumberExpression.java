package com.lampirg.calculator.logic.expression;

import java.util.function.BiFunction;


public class BinaryNumberExpression extends BinaryExpression {
    private final double x1;
    private final double x2;

    public BinaryNumberExpression(double x1, double x2, BiFunction<Double, Double, Double> operation) {
        super(new NumberExpression(x1), new NumberExpression(x2), operation);
        this.x1 = x1;
        this.x2 = x2;
    }

    @Override
    public Double compute() {
        return operation.apply(x1, x2);
    }
}
