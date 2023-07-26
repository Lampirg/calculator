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

// TODO: refactor mocking
@ExtendWith(MockitoExtension.class)
public class TestParser {

    @Mock
    private ExpressionParser expressionParser;
    @InjectMocks
    private Parser parser;

    @Test
    void givenSimpleBrackets() {
        // Regex for one number "^[\\d]+[\\.][\\d]+$" (I'm done with regex so this comment should be deleted soon)
        Mockito.doReturn(new NumberExpression(12)).when(expressionParser).parse("12");
        Mockito.doReturn(new NumberExpression(24)).when(expressionParser).parse("24");
        Mockito.doReturn(new Sum(12, 24)).when(expressionParser).parse("12.0+24.0");
        Mockito.doReturn(new Subtract(12, 24)).when(expressionParser).parse("12.0-24.0");
        Mockito.doReturn(new Multiply(12, 24)).when(expressionParser).parse("12.0*24.0");
        Mockito.doReturn(new Divide(12, 24)).when(expressionParser).parse("12.0/24.0");
        Assertions.assertEquals(new Sum(12, 24), parser.parse("(12)+(24)"));
        Assertions.assertEquals(new Subtract(12, 24), parser.parse("(12)-(24)"));
        Assertions.assertEquals(new Multiply(12, 24), parser.parse("(12)*(24)"));
        Assertions.assertEquals(new Divide(12, 24), parser.parse("(12)/(24)"));
    }

    @Test
    void givenMultipleSimpleBrackets() {
        mockExpressions();
        Assertions.assertEquals(new NumberExpression(36), parser.parse("(((12))+((24)))"));
        Assertions.assertEquals(new NumberExpression(-12), parser.parse("((12)-24)"));
        Assertions.assertEquals(new Multiply(12, 24), parser.parse("(12)*((24))"));
        Assertions.assertEquals(new NumberExpression(0.5), parser.parse("((12)/(((24))))"));
    }

    private void mockExpressions() {
        Mockito.doReturn(new NumberExpression(12)).when(expressionParser).parse("12");
        Mockito.doReturn(new NumberExpression(24)).when(expressionParser).parse("24");
        Mockito.doReturn(new NumberExpression(12)).when(expressionParser).parse("12.0");
        Mockito.doReturn(new NumberExpression(24)).when(expressionParser).parse("24.0");
        Mockito.doReturn(new NumberExpression(36)).when(expressionParser).parse("36.0");
        Mockito.doReturn(new NumberExpression(-12)).when(expressionParser).parse("-12.0");
        Mockito.doReturn(new NumberExpression(0.5)).when(expressionParser).parse("0.5");
        Mockito.doReturn(new Sum(12, 24)).when(expressionParser).parse("12.0+24.0");
        Mockito.doReturn(new Subtract(12, 24)).when(expressionParser).parse("12.0-24");
        Mockito.doReturn(new Multiply(12, 24)).when(expressionParser).parse("12.0*24.0");
        Mockito.doReturn(new Divide(12, 24)).when(expressionParser).parse("12.0/24.0");
    }
}
