package com.lampirg.calculator.logic.expression;

public class Sum extends BinaryNumberExpression {

    public Sum(double x1, double x2) {
        super(x1, x2, Double::sum);
    }
}
