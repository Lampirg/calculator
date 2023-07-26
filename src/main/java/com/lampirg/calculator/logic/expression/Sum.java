package com.lampirg.calculator.logic.expression;

import java.util.function.BiFunction;

public class Sum extends BinaryExpression {

    public Sum(int x1, int x2) {
        super(x1, x2, Integer::sum);
    }
}
