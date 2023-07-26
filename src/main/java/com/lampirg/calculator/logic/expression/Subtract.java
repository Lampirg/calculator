package com.lampirg.calculator.logic.expression;

import java.util.function.BiFunction;

public class Subtract extends BinaryExpression {
    public Subtract(double x1, double x2) {
        super(x1, x2, (n1, n2) -> n1-n2);
    }
}
