package com.lampirg.calculator.logic.expression;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NumberExpression implements DoubleExpression {

    private final double number;

    @Override
    public Double compute() {
        return number;
    }
}
