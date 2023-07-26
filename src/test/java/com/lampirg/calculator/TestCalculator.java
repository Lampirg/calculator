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
}
