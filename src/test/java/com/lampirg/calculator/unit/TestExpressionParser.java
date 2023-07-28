package com.lampirg.calculator.unit;

import com.lampirg.calculator.logic.expression.number.*;
import com.lampirg.calculator.logic.parse.ExpressionParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestExpressionParser {
    
    private ExpressionParser expressionParser = new ExpressionParser();
    
    @Test
    void givenSimplestExpressions() {
        Assertions.assertEquals(new Sum(2, 2), expressionParser.parse("2+2"));
        Assertions.assertEquals(new Subtract(2, 2), expressionParser.parse("2-2"));
        Assertions.assertEquals(new Multiply(4, 2), expressionParser.parse("4*2"));
        Assertions.assertEquals(new Divide(8, 2), expressionParser.parse("8/2"));
    }

    @Test
    void givenMinusAtBeginning() {
        Assertions.assertEquals(new Sum(-2, 2), expressionParser.parse("-2+2"));
        Assertions.assertEquals(new Subtract(-2, 2), expressionParser.parse("-2-2"));
        Assertions.assertEquals(new Multiply(-4, 2), expressionParser.parse("-4*2"));
        Assertions.assertEquals(new Divide(-8, 2), expressionParser.parse("-8/2"));
    }

    @Test
    void givenNotDigitNumbers() {
        Assertions.assertEquals(new Sum(12, 24), expressionParser.parse("12+24"));
        Assertions.assertEquals(new Subtract(25, 11), expressionParser.parse("25-11"));
        Assertions.assertEquals(new Multiply(15, 10), expressionParser.parse("15*10"));
        Assertions.assertEquals(new Divide(125, 25), expressionParser.parse("125/25"));
    }

    @Test
    void givenNotDigitNumbersWithMinus() {
        Assertions.assertEquals(new Sum(-12, 24), expressionParser.parse("-12+24"));
        Assertions.assertEquals(new Subtract(-25, 11), expressionParser.parse("-25-11"));
        Assertions.assertEquals(new Multiply(-15, 10), expressionParser.parse("-15*10"));
        Assertions.assertEquals(new Divide(-125, 25), expressionParser.parse("-125/25"));
    }

    @Test
    void givenNonInteger() {
        Assertions.assertEquals(new Divide(5, 2), expressionParser.parse("5/2"));
        Assertions.assertEquals(new Sum(2.5, 2), expressionParser.parse("2.5+2"));
        Assertions.assertEquals(new Subtract(2.5, 2.5), expressionParser.parse("2.5-2.5"));
        Assertions.assertEquals(new Multiply(2, 2.5), expressionParser.parse("2*2.5"));
        Assertions.assertEquals(new Divide(9.74, 9.74), expressionParser.parse("9.74/9.74"));
    }

    @Test
    void givenNonIntegerWithLeadingNegative() {
        Assertions.assertEquals(new Divide(-5, 2), expressionParser.parse("-5/2"));
        Assertions.assertEquals(new Sum(-2.5, 2), expressionParser.parse("-2.5+2"));
        Assertions.assertEquals(new Subtract(-2.5, 2.5), expressionParser.parse("-2.5-2.5"));
        Assertions.assertEquals(new Multiply(-2, 2.5), expressionParser.parse("-2*2.5"));
        Assertions.assertEquals(new Divide(-9.74, 9.74), expressionParser.parse("-9.74/9.74"));
    }

    @Test
    void givenOneNumber() {
        Assertions.assertEquals(new NumberExpression(12), expressionParser.parse("12"));
        Assertions.assertEquals(new NumberExpression(-12), expressionParser.parse("-12"));
        Assertions.assertEquals(new NumberExpression(1.2), expressionParser.parse("1.2"));
        Assertions.assertEquals(new NumberExpression(-1.2), expressionParser.parse("-1.2"));
    }

    @Test
    void givenDivideByNegative() {
        Assertions.assertEquals(new Divide(5, -20), expressionParser.parse("5/-20.0"));
    }
}
