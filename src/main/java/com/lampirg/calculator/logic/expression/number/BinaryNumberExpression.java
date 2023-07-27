package com.lampirg.calculator.logic.expression.number;

import com.lampirg.calculator.logic.expression.BinaryExpression;

import java.util.function.BiFunction;


public class BinaryNumberExpression extends BinaryExpression {
    public BinaryNumberExpression(double x1, double x2, BiFunction<Double, Double, Double> operation) {
        super(new NumberExpression(x1), new NumberExpression(x2), operation);
    }
}
