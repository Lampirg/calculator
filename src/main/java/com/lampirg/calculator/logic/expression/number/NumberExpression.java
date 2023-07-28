package com.lampirg.calculator.logic.expression.number;

import com.lampirg.calculator.logic.expression.DoubleExpression;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class NumberExpression implements DoubleExpression {

    private final double number;

    @Override
    public Double compute() {
        return number;
    }
}
