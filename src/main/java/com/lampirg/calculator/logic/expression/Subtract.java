package com.lampirg.calculator.logic.expression;

public class Subtract extends BinaryNumberExpression {
    public Subtract(double x1, double x2) {
        super(x1, x2, (n1, n2) -> n1-n2);
    }
}
