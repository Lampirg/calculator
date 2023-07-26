package com.lampirg.calculator.logic.expression;

import java.util.function.BiFunction;

public class Sum extends BinaryExpression {

    public Sum(double x1, double x2) {
        super(x1, x2, Double::sum);
    }
}
