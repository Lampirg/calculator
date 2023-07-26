package com.lampirg.calculator.logic.expression;

public class Divide extends BinaryNumberExpression {
    public Divide(double x1, double x2) {
        super(x1, x2, (n1, n2) -> n1 / n2);
    }
}
