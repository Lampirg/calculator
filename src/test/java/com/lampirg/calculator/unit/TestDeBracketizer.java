package com.lampirg.calculator.unit;

import com.lampirg.calculator.logic.expression.number.Multiply;
import com.lampirg.calculator.logic.expression.number.NumberExpression;
import com.lampirg.calculator.logic.expression.number.Subtract;
import com.lampirg.calculator.logic.parse.Parser;
import com.lampirg.calculator.logic.parse.bracket.DeBracketizer;
import com.lampirg.calculator.logic.parse.bracket.RightBracketExpressionFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestDeBracketizer {

    @Mock
    private RightBracketExpressionFinder bracketFinder;
    @Mock
    private Parser parser;
    @InjectMocks
    private DeBracketizer deBracketizer;

    @Test
    void givenSimpleExpression() {
        String inputString = "(15)";
        Mockito.when(bracketFinder.findForwardStringInBrackets(inputString, 0)).thenReturn("15");
        Mockito.when(parser.parse("15")).thenReturn(new NumberExpression(15));
        Assertions.assertEquals("15.0", deBracketizer.deBracketize(inputString));
    }

    @Test
    void givenNoBrackets() {
        Assertions.assertEquals("15", deBracketizer.deBracketize("15"));
        Assertions.assertEquals("15+25-11", deBracketizer.deBracketize("15+25-11"));
    }

    @Test
    void givenThreeExpressions() {
        String inputString = "1+(3*15)";
        String bracketFinderResult = "3*15";
        Mockito.when(bracketFinder.findForwardStringInBrackets(inputString, 2)).thenReturn(bracketFinderResult);
        Mockito.when(parser.parse(bracketFinderResult)).thenReturn(new Multiply(3, 15));
        Assertions.assertEquals("1+45.0", deBracketizer.deBracketize(inputString));
    }

    @Test
    void givenMultipleBrackets() {
        String inputString = "5/(25-(3*15))";
        String bracketFinderResult = "25-(3*15)";
        Mockito.when(bracketFinder.findForwardStringInBrackets(inputString, 2)).thenReturn(bracketFinderResult);
        Mockito.when(parser.parse(bracketFinderResult)).thenReturn(new Subtract(25, 45));
        Assertions.assertEquals("5/-20.0", deBracketizer.deBracketize(inputString));
    }
}
