package com.lampirg.calculator.logic.expression.number;

public class Multiply extends BinaryNumberExpression {
    public Multiply(double x1, double x2) {
        super(x1, x2, (n1, n2) -> n1 * n2);
    }
}
