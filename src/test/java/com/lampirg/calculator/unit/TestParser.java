package com.lampirg.calculator.unit;

import com.lampirg.calculator.logic.expression.number.*;
import com.lampirg.calculator.logic.parse.bracket.BracketFinder;
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
    @Mock
    private BracketFinder bracketFinder;
    @InjectMocks
    private Parser parser;

    private void mockBasicNumbers() {
        Mockito.doReturn(new NumberExpression(12)).when(expressionParser).parse("12");
        Mockito.doReturn(new NumberExpression(24)).when(expressionParser).parse("24");
    }

    @Test
    void sumGivenSimpleBrackets() {
        String inputExpression = "(12)+(24)";
        mockBasicNumbers();
        Mockito.doReturn(new Sum(12, 24)).when(expressionParser).parse("12.0+24.0");
        Mockito.doReturn("12").when(bracketFinder).findForwardStringInBrackets(inputExpression, 0);
        Mockito.doReturn("24").when(bracketFinder).findForwardStringInBrackets("12.0+(24)", 5);
        Assertions.assertEquals(new Sum(12, 24), parser.parse(inputExpression));
    }

    @Test
    void subGivenSimpleBrackets() {
        String inputExpression = "(12)-(24)";
        mockBasicNumbers();
        Mockito.doReturn(new Subtract(12, 24)).when(expressionParser).parse("12.0-24.0");
        Mockito.doReturn("12").when(bracketFinder).findForwardStringInBrackets(inputExpression, 0);
        Mockito.doReturn("24").when(bracketFinder).findForwardStringInBrackets("12.0-(24)", 5);
        Assertions.assertEquals(new Subtract(12, 24), parser.parse(inputExpression));
    }

    @Test
    void mulGivenSimpleBrackets() {
        String inputExpression = "(12)*(24)";
        mockBasicNumbers();
        Mockito.doReturn(new Multiply(12, 24)).when(expressionParser).parse("12.0*24.0");
        Mockito.doReturn("12").when(bracketFinder).findForwardStringInBrackets(inputExpression, 0);
        Mockito.doReturn("24").when(bracketFinder).findForwardStringInBrackets("12.0*(24)", 5);
        Assertions.assertEquals(new Multiply(12, 24), parser.parse(inputExpression));
    }

    @Test
    void divGivenSimpleBrackets() {
        String inputExpression = "(12)/(24)";
        mockBasicNumbers();
        Mockito.doReturn(new Divide(12, 24)).when(expressionParser).parse("12.0/24.0");
        Mockito.doReturn("12").when(bracketFinder).findForwardStringInBrackets(inputExpression, 0);
        Mockito.doReturn("24").when(bracketFinder).findForwardStringInBrackets("12.0/(24)", 5);
        Assertions.assertEquals(new Divide(12, 24), parser.parse(inputExpression));
    }

    @Test
    void sumGivenMultipleSimpleBrackets() {
        String inputExpression = "(((12))+((24)))";
        mockBasicNumbers();
        Mockito.doReturn(new NumberExpression(12)).when(expressionParser).parse("12.0");
        Mockito.doReturn(new NumberExpression(24)).when(expressionParser).parse("24.0");
        Mockito.doReturn(new NumberExpression(36)).when(expressionParser).parse("36.0");
        Mockito.doReturn(new Sum(12, 24)).when(expressionParser).parse("12.0+24.0");
        Mockito.doReturn("((12))+((24))").when(bracketFinder).findForwardStringInBrackets(inputExpression, 0);
        Mockito.doReturn("(12)").when(bracketFinder).findForwardStringInBrackets("((12))+((24))", 0);
        Mockito.doReturn("12").when(bracketFinder).findForwardStringInBrackets("(12)", 0);
        Mockito.doReturn("(24)").when(bracketFinder).findForwardStringInBrackets("12.0+((24))", 5);
        Mockito.doReturn("24").when(bracketFinder).findForwardStringInBrackets("(24)", 0);
        Assertions.assertEquals(new NumberExpression(36), parser.parse(inputExpression));
    }

    @Test
    void subGivenMultipleSimpleBrackets() {
        String inputExpression = "((12)-24)";
        Mockito.doReturn(new NumberExpression(12)).when(expressionParser).parse("12");
        Mockito.doReturn(new NumberExpression(-12)).when(expressionParser).parse("-12.0");
        Mockito.doReturn(new Subtract(12, 24)).when(expressionParser).parse("12.0-24");
        Mockito.doReturn("(12)-24").when(bracketFinder).findForwardStringInBrackets(inputExpression, 0);
        Mockito.doReturn("12").when(bracketFinder).findForwardStringInBrackets("(12)-24", 0);
        Assertions.assertEquals(new NumberExpression(-12), parser.parse(inputExpression));
    }

    @Test
    void mulGivenMultipleSimpleBrackets() {
        String inputExpression = "(12)*((24))";
        mockBasicNumbers();
        Mockito.doReturn(new NumberExpression(24)).when(expressionParser).parse("24.0");
        Mockito.doReturn(new Multiply(12, 24)).when(expressionParser).parse("12.0*24.0");
        Mockito.doReturn("12").when(bracketFinder).findForwardStringInBrackets(inputExpression, 0);
        Mockito.doReturn("(24)").when(bracketFinder).findForwardStringInBrackets("12.0*((24))", 5);
        Mockito.doReturn("24").when(bracketFinder).findForwardStringInBrackets("(24)", 0);
        Assertions.assertEquals(new Multiply(12, 24), parser.parse(inputExpression));
    }

    @Test
    void divGivenMultipleSimpleBrackets() {
        String inputExpression = "((12)/(((24))))";
        mockBasicNumbers();
        Mockito.doReturn(new NumberExpression(24)).when(expressionParser).parse("24.0");
        Mockito.doReturn(new NumberExpression(0.5)).when(expressionParser).parse("0.5");
        Mockito.doReturn(new Divide(12, 24)).when(expressionParser).parse("12.0/24.0");
        Mockito.doReturn("(12)/(((24)))").when(bracketFinder).findForwardStringInBrackets(inputExpression, 0);
        Mockito.doReturn("12").when(bracketFinder).findForwardStringInBrackets("(12)/(((24)))", 0);
        Mockito.doReturn("((24))").when(bracketFinder).findForwardStringInBrackets("12.0/(((24)))", 5);
        Mockito.doReturn("(24)").when(bracketFinder).findForwardStringInBrackets("((24))", 0);
        Mockito.doReturn("24").when(bracketFinder).findForwardStringInBrackets("(24)", 0);
        Assertions.assertEquals(new NumberExpression(0.5), parser.parse(inputExpression));
    }
}
