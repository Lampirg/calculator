package com.lampirg.calculator;

import com.lampirg.calculator.logic.SimpleCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCalculator {

    @Autowired
    private SimpleCalculator simpleCalculator;

    @Test
    void givenSimplestExpressions() {
        Assertions.assertEquals("4", simpleCalculator.calculate("2+2"));
        Assertions.assertEquals("0", simpleCalculator.calculate("2-2"));
        Assertions.assertEquals("8", simpleCalculator.calculate("4*2"));
        Assertions.assertEquals("4", simpleCalculator.calculate("8/2"));
    }

    @Test
    void givenMinusAtBeginning() {
        Assertions.assertEquals("0", simpleCalculator.calculate("-2+2"));
        Assertions.assertEquals("-4", simpleCalculator.calculate("-2-2"));
        Assertions.assertEquals("-8", simpleCalculator.calculate("-4*2"));
        Assertions.assertEquals("-4", simpleCalculator.calculate("-8/2"));
    }

    @Test
    void givenNotDigitNumbers() {
        Assertions.assertEquals("36", simpleCalculator.calculate("12+24"));
        Assertions.assertEquals("14", simpleCalculator.calculate("25-11"));
        Assertions.assertEquals("150", simpleCalculator.calculate("15*10"));
        Assertions.assertEquals("5", simpleCalculator.calculate("125/25"));
    }

    @Test
    void givenNotDigitNumbersWithMinus() {
        Assertions.assertEquals("12", simpleCalculator.calculate("-12+24"));
        Assertions.assertEquals("-36", simpleCalculator.calculate("-25-11"));
        Assertions.assertEquals("-150", simpleCalculator.calculate("-15*10"));
        Assertions.assertEquals("-5", simpleCalculator.calculate("-125/25"));
    }

    @Test
    void givenNonInteger() {
        Assertions.assertEquals("2.5", simpleCalculator.calculate("5/2"));
        Assertions.assertEquals("4.5", simpleCalculator.calculate("2.5+2"));
        Assertions.assertEquals("0", simpleCalculator.calculate("2.5-2.5"));
        Assertions.assertEquals("5", simpleCalculator.calculate("2*2.5"));
        Assertions.assertEquals("1", simpleCalculator.calculate("9.74/9.74"));
    }

    @Test
    void givenNonIntegerWithLeadingNegative() {
        Assertions.assertEquals("-2.5", simpleCalculator.calculate("-5/2"));
        Assertions.assertEquals("-0.5", simpleCalculator.calculate("-2.5+2"));
        Assertions.assertEquals("-5", simpleCalculator.calculate("-2.5-2.5"));
        Assertions.assertEquals("-5", simpleCalculator.calculate("-2*2.5"));
        Assertions.assertEquals("-1", simpleCalculator.calculate("-9.74/9.74"));
    }

    @Test
    void givenBrackets() {
        // TODO
//        Assertions.assertEquals("36", simpleCalculator.calculate("(12)+(24)"));
//        Assertions.assertEquals("14", simpleCalculator.calculate("(25)-(11)"));
//        Assertions.assertEquals("150", simpleCalculator.calculate("(15)*(10)"));
//        Assertions.assertEquals("5", simpleCalculator.calculate("(125)/(25)"));
    }

    @Test
    void givenThreeUnorderedExpressions() {
        Assertions.assertEquals("10", simpleCalculator.calculate("4+5+1"));
        Assertions.assertEquals("21", simpleCalculator.calculate("4*5+1"));
    }

    @Test
    void givenThreeOrderedExpressions() {
//        Assertions.assertEquals("21", simpleCalculator.calculate("1+4*5")); TODO
    }
}
