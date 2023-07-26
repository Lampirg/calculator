package com.lampirg.calculator.logic.expression;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NumberExpression implements Expression<Double> {

    private final double number;

    @Override
    public Double compute() {
        return number;
    }
}
