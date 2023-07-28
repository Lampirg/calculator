package com.lampirg.calculator.integrate;

import com.lampirg.calculator.logic.expression.number.Multiply;
import com.lampirg.calculator.logic.expression.number.Sum;
import com.lampirg.calculator.logic.parse.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DisplayName("Integration test of parser and its dependencies")
public class TestParserWithComponents {

    @Autowired
    private Parser parser;


    @Test
    @DisplayName("Test simple expression")
    void simpleMul() {
        String inputExpression = "4*2";
        Assertions.assertEquals(new Multiply(4, 2), parser.parse(inputExpression));
    }

    @Test
    @DisplayName("Test simple expression with numbers under brackets")
    void sumGivenSimpleBrackets() {
        String inputExpression = "(12)+(24)";
        Assertions.assertEquals(new Sum(12, 24), parser.parse(inputExpression));
    }

    @Test
    @DisplayName("Test simple expression with numbers under many brackets")
    void mulGivenMultipleSimpleBrackets() {
        String inputExpression = "(12)*((24))";
        Assertions.assertEquals(new Multiply(12, 24), parser.parse(inputExpression));
    }

    @Test
    @DisplayName("Test three operations")
    void givenThreeOrderedExpressions() {
        Assertions.assertEquals(new Sum(1, 20), parser.parse("1+(4*5)"));
    }

}
