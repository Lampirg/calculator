package com.lampirg.calculator.logic.expression.number;

import com.lampirg.calculator.logic.expression.DoubleExpression;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NumberExpression implements DoubleExpression {

    private final double number;

    @Override
    public Double compute() {
        return number;
    }
}
