package com.lampirg.calculator.integrate;

import com.lampirg.calculator.logic.SimpleCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Test whole calculator")
public class TestCalculator {

    @Autowired
    private SimpleCalculator simpleCalculator;

    @Test
    @DisplayName("Test simple expressions")
    void givenSimplestExpressions() {
        Assertions.assertEquals("4", simpleCalculator.calculate("2+2"));
        Assertions.assertEquals("0", simpleCalculator.calculate("2-2"));
        Assertions.assertEquals("8", simpleCalculator.calculate("4*2"));
        Assertions.assertEquals("4", simpleCalculator.calculate("8/2"));
    }

    @Test
    @DisplayName("Test simple expressions with minus")
    void givenMinusAtBeginning() {
        Assertions.assertEquals("0", simpleCalculator.calculate("-2+2"));
        Assertions.assertEquals("-4", simpleCalculator.calculate("-2-2"));
        Assertions.assertEquals("-8", simpleCalculator.calculate("-4*2"));
        Assertions.assertEquals("-4", simpleCalculator.calculate("-8/2"));
    }

    @Test
    @DisplayName("Test simple expressions with numbers > 10")
    void givenNotDigitNumbers() {
        Assertions.assertEquals("36", simpleCalculator.calculate("12+24"));
        Assertions.assertEquals("14", simpleCalculator.calculate("25-11"));
        Assertions.assertEquals("150", simpleCalculator.calculate("15*10"));
        Assertions.assertEquals("5", simpleCalculator.calculate("125/25"));
    }

    @Test
    @DisplayName("Test simple expressions with negative numbers > 10")
    void givenNotDigitNumbersWithMinus() {
        Assertions.assertEquals("12", simpleCalculator.calculate("-12+24"));
        Assertions.assertEquals("-36", simpleCalculator.calculate("-25-11"));
        Assertions.assertEquals("-150", simpleCalculator.calculate("-15*10"));
        Assertions.assertEquals("-5", simpleCalculator.calculate("-125/25"));
    }

    @Test
    @DisplayName("Test non-integer numbers")
    void givenNonInteger() {
        Assertions.assertEquals("2.5", simpleCalculator.calculate("5/2"));
        Assertions.assertEquals("4.5", simpleCalculator.calculate("2.5+2"));
        Assertions.assertEquals("0", simpleCalculator.calculate("2.5-2.5"));
        Assertions.assertEquals("5", simpleCalculator.calculate("2*2.5"));
        Assertions.assertEquals("1", simpleCalculator.calculate("9.74/9.74"));
    }

    @Test
    @DisplayName("Test non-integer negative numbers")
    void givenNonIntegerWithLeadingNegative() {
        Assertions.assertEquals("-2.5", simpleCalculator.calculate("-5/2"));
        Assertions.assertEquals("-0.5", simpleCalculator.calculate("-2.5+2"));
        Assertions.assertEquals("-5", simpleCalculator.calculate("-2.5-2.5"));
        Assertions.assertEquals("-5", simpleCalculator.calculate("-2*2.5"));
        Assertions.assertEquals("-1", simpleCalculator.calculate("-9.74/9.74"));
    }

    @Test
    @DisplayName("Test simple expressions with numbers under brackets")
    void givenSimpleBrackets() {
        Assertions.assertEquals("36", simpleCalculator.calculate("(12)+(24)"));
        Assertions.assertEquals("14", simpleCalculator.calculate("(25)-(11)"));
        Assertions.assertEquals("150", simpleCalculator.calculate("(15)*(10)"));
        Assertions.assertEquals("5", simpleCalculator.calculate("(125)/(25)"));
    }

    @Test
    @DisplayName("Test simple expressions with numbers under many brackets")
    void givenManySimpleBrackets() {
        Assertions.assertEquals("36", simpleCalculator.calculate("((12))+((24))"));
        Assertions.assertEquals("14", simpleCalculator.calculate("((25)-(11))"));
        Assertions.assertEquals("150", simpleCalculator.calculate("(((15))*(10))"));
        Assertions.assertEquals("5", simpleCalculator.calculate("(((125))/((25)))"));
    }

    @Test
    @DisplayName("Test three operations")
    void givenThreeUnorderedExpressions() {
        Assertions.assertEquals("10", simpleCalculator.calculate("4+5+1"));
        Assertions.assertEquals("21", simpleCalculator.calculate("4*5+1"));
    }

    @Test
    @DisplayName("Test three operations where multiplying must be performed before addition")
    void givenThreeExpressionsWithBrackets() {
        Assertions.assertEquals("10", simpleCalculator.calculate("((4+5)+1)"));
        Assertions.assertEquals("24", simpleCalculator.calculate("4*(5+1)"));
        Assertions.assertEquals("21", simpleCalculator.calculate("((4*5)+1)"));
        Assertions.assertEquals("21", simpleCalculator.calculate("1+(4*5)"));
    }

    @Test
    @DisplayName("Test division with brackets")
    void givenDivWithBrackets() {
        Assertions.assertEquals("-0.25", simpleCalculator.calculate("5/(25-(3*15))"));
    }

    @Test
    @DisplayName("Test 1+4*5")
    void givenThreeOrderedExpressions() {
        Assertions.assertEquals("21", simpleCalculator.calculate("1+4*5"));
    }
}
