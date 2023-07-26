package com.lampirg.calculator;

import com.lampirg.calculator.logic.SimpleCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCalculator {

    private SimpleCalculator simpleCalculator = new SimpleCalculator();

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
}
