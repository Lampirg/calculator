package com.lampirg.calculator.unit;

import com.lampirg.calculator.logic.expression.number.*;
import com.lampirg.calculator.logic.parse.ExpressionParser;
import com.lampirg.calculator.logic.parse.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestParser {

    @Mock
    private ExpressionParser expressionParser;
    @InjectMocks
    private Parser parser;

    @Test
    void sumGivenSimpleBrackets() {
        mockBasicNumbers();
        Mockito.doReturn(new Sum(12, 24)).when(expressionParser).parse("12.0+24.0");
        Assertions.assertEquals(new Sum(12, 24), parser.parse("(12)+(24)"));
    }

    private void mockBasicNumbers() {
        Mockito.doReturn(new NumberExpression(12)).when(expressionParser).parse("12");
        Mockito.doReturn(new NumberExpression(24)).when(expressionParser).parse("24");
    }

    @Test
    void subGivenSimpleBrackets() {
        mockBasicNumbers();
        Mockito.doReturn(new Subtract(12, 24)).when(expressionParser).parse("12.0-24.0");
        Assertions.assertEquals(new Subtract(12, 24), parser.parse("(12)-(24)"));
    }

    @Test
    void mulGivenSimpleBrackets() {
        mockBasicNumbers();
        Mockito.doReturn(new Multiply(12, 24)).when(expressionParser).parse("12.0*24.0");
        Assertions.assertEquals(new Multiply(12, 24), parser.parse("(12)*(24)"));
    }

    @Test
    void divGivenSimpleBrackets() {
        mockBasicNumbers();
        Mockito.doReturn(new Divide(12, 24)).when(expressionParser).parse("12.0/24.0");
        Assertions.assertEquals(new Divide(12, 24), parser.parse("(12)/(24)"));
    }

    @Test
    void sumGivenMultipleSimpleBrackets() {
        mockBasicNumbers();
        Mockito.doReturn(new NumberExpression(12)).when(expressionParser).parse("12.0");
        Mockito.doReturn(new NumberExpression(24)).when(expressionParser).parse("24.0");
        Mockito.doReturn(new NumberExpression(36)).when(expressionParser).parse("36.0");
        Mockito.doReturn(new Sum(12, 24)).when(expressionParser).parse("12.0+24.0");
        Assertions.assertEquals(new NumberExpression(36), parser.parse("(((12))+((24)))"));
    }

    @Test
    void subGivenMultipleSimpleBrackets() {
        Mockito.doReturn(new NumberExpression(12)).when(expressionParser).parse("12");
        Mockito.doReturn(new NumberExpression(-12)).when(expressionParser).parse("-12.0");
        Mockito.doReturn(new Subtract(12, 24)).when(expressionParser).parse("12.0-24");
        Assertions.assertEquals(new NumberExpression(-12), parser.parse("((12)-24)"));
    }

    @Test
    void mulGivenMultipleSimpleBrackets() {
        mockBasicNumbers();
        Mockito.doReturn(new NumberExpression(24)).when(expressionParser).parse("24.0");
        Mockito.doReturn(new Multiply(12, 24)).when(expressionParser).parse("12.0*24.0");
        Assertions.assertEquals(new Multiply(12, 24), parser.parse("(12)*((24))"));
    }

    @Test
    void divGivenMultipleSimpleBrackets() {
        mockBasicNumbers();
        Mockito.doReturn(new NumberExpression(24)).when(expressionParser).parse("24.0");
        Mockito.doReturn(new NumberExpression(0.5)).when(expressionParser).parse("0.5");
        Mockito.doReturn(new Divide(12, 24)).when(expressionParser).parse("12.0/24.0");
        Assertions.assertEquals(new NumberExpression(0.5), parser.parse("((12)/(((24))))"));
    }
}
