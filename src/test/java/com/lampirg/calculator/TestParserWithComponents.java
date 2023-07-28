package com.lampirg.calculator;

import com.lampirg.calculator.logic.expression.number.Multiply;
import com.lampirg.calculator.logic.expression.number.Sum;
import com.lampirg.calculator.logic.parse.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TestParserWithComponents {

    @Autowired
    private Parser parser;


    @Test
    void simpleMul() {
        String inputExpression = "4*2";
        Assertions.assertEquals(new Multiply(4, 2), parser.parse(inputExpression));
    }

    @Test
    void sumGivenSimpleBrackets() {
        String inputExpression = "(12)+(24)";
        Assertions.assertEquals(new Sum(12, 24), parser.parse(inputExpression));
    }

    @Test
    void mulGivenMultipleSimpleBrackets() {
        String inputExpression = "(12)*((24))";
        Assertions.assertEquals(new Multiply(12, 24), parser.parse(inputExpression));
    }

    @Test
    void givenThreeOrderedExpressions() {
        Assertions.assertEquals(new Sum(1, 20), parser.parse("1+(4*5)"));
    }

}
