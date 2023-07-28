package com.lampirg.calculator.unit;

import com.lampirg.calculator.logic.parse.bracket.BracketExpressionFinder;
import com.lampirg.calculator.logic.parse.bracket.Bracketizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestBracketizer {

    @Mock
    private BracketExpressionFinder bracketFinder;
    @InjectMocks
    private Bracketizer bracketizer;


    @Test
    void givenSimpleSum() {
        Assertions.assertEquals("3+5", bracketizer.bracketize("3+5"));
    }

    @Test
    void givenSimpleMul() {
        Assertions.assertEquals("(4*2)", bracketizer.bracketize("4*2"));
    }

    @Test
    void givenSimpleMulWithMinus() {
        Assertions.assertEquals("(-4*2)", bracketizer.bracketize("-4*2"));
    }

    @Test
    void givenThreeOrderedExpressions() {
        Assertions.assertEquals("1+(4*5)", bracketizer.bracketize("1+4*5"));
    }

    @Test
    void givenNonInteger() {
        Assertions.assertEquals("(-2*2.5)", bracketizer.bracketize("-2*2.5"));
    }

    @Test
    void givenAdditionAndMultiplication() {
        Assertions.assertEquals("1+(3*4)", bracketizer.bracketize("1+3*4"));
        Assertions.assertEquals("(1+(3*4))", bracketizer.bracketize("(1+3*4)"));
    }

    @Test
    void givenBracesFromLeft() {
        String input = "(1+3)*4";
        Mockito.when(bracketFinder.findBackwardsStringInBrackets(input, 4))
                .thenReturn("1+3");
        Assertions.assertEquals("((1+3)*4)", bracketizer.bracketize(input));
    }

    @Test
    void givenBracesFromRight() {
        String input = "1+3*(4+6)";
        Mockito.when(bracketFinder.findForwardStringInBrackets(input, 4))
                .thenReturn("1+3");
        Assertions.assertEquals("1+(3*(4+6))", bracketizer.bracketize(input));
    }

    @Test
    void givenBracesFromBoth() {
        String input = "(15+1)+(1+3)*(4+6)";
        Mockito.when(bracketFinder.findForwardStringInBrackets(input, 13))
                .thenReturn("4+6");
        Mockito.when(bracketFinder.findBackwardsStringInBrackets(input, 11))
                .thenReturn("1+3");
        Assertions.assertEquals("(15+1)+((1+3)*(4+6))", bracketizer.bracketize(input));
    }

    @Test
    void givenOnlyBraces() {
        String input = "(3*4)";
        Mockito.when(bracketFinder.findForwardStringInBrackets(input, 0))
                .thenReturn("3*4");
        Assertions.assertEquals(input, bracketizer.bracketize(input));
    }
}
