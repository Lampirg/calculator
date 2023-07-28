package com.lampirg.calculator.unit;

import com.lampirg.calculator.logic.parse.bracket.BracketExpressionFinder;
import com.lampirg.calculator.logic.parse.bracket.Bracketizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Bracketizer")
public class TestBracketizer {

    @Mock
    private BracketExpressionFinder bracketFinder;
    @InjectMocks
    private Bracketizer bracketizer;


    @Test
    @DisplayName("Test not putting brackets into simple sum")
    void givenSimpleSum() {
        Assertions.assertEquals("3+5", bracketizer.bracketize("3+5"));
    }

    @Test
    @DisplayName("Test putting brackets into simple multiplying")
    void givenSimpleMul() {
        Assertions.assertEquals("(4*2)", bracketizer.bracketize("4*2"));
    }

    @Test
    @DisplayName("Test putting brackets into simple dividing")
    void givenSimpleDiv() {
        Assertions.assertEquals("(4/2)", bracketizer.bracketize("4/2"));
    }

    @Test
    @DisplayName("Test putting brackets into simple multiplying with minus at the start")
    void givenSimpleMulWithMinus() {
        Assertions.assertEquals("(-4*2)", bracketizer.bracketize("-4*2"));
    }

    @Test
    @DisplayName("Test putting brackets into multiplying after")
    void givenThreeOrderedExpressions() {
        Assertions.assertEquals("1+(4*5)", bracketizer.bracketize("1+4*5"));
    }

    @Test
    @DisplayName("Test putting brackets when number is non-integer")
    void givenNonInteger() {
        Assertions.assertEquals("(-2*2.5)", bracketizer.bracketize("-2*2.5"));
    }

    @Test
    @DisplayName("Test putting brackets into multiplying when whole expression in brackets")
    void givenAdditionAndMultiplication() {
        Assertions.assertEquals("(1+(3*4))", bracketizer.bracketize("(1+3*4)"));
    }

    @Test
    @DisplayName("Test putting brackets when left part is a sum under brackets")
    void givenBracesFromLeft() {
        String input = "(1+3)*4";
        Mockito.when(bracketFinder.findBackwardsStringInBrackets(input, 4))
                .thenReturn("1+3");
        Assertions.assertEquals("((1+3)*4)", bracketizer.bracketize(input));
    }

    @Test
    @DisplayName("Test putting brackets when right part is a sum under brackets")
    void givenBracesFromRight() {
        String input = "1+3*(4+6)";
        Mockito.when(bracketFinder.findForwardStringInBrackets(input, 4))
                .thenReturn("1+3");
        Assertions.assertEquals("1+(3*(4+6))", bracketizer.bracketize(input));
    }

    @Test
    @DisplayName("Test putting brackets when both parts are under brackets")
    void givenBracesFromBoth() {
        String input = "(15+1)+(1+3)*(4+6)";
        Mockito.when(bracketFinder.findForwardStringInBrackets(input, 13))
                .thenReturn("4+6");
        Mockito.when(bracketFinder.findBackwardsStringInBrackets(input, 11))
                .thenReturn("1+3");
        Assertions.assertEquals("(15+1)+((1+3)*(4+6))", bracketizer.bracketize(input));
    }

    @Test
    @DisplayName("Test not putting brackets when expression already under brackets")
    void givenOnlyBraces() {
        String input = "(3*4)";
        Mockito.when(bracketFinder.findForwardStringInBrackets(input, 0))
                .thenReturn("3*4");
        Assertions.assertEquals(input, bracketizer.bracketize(input));
    }
}
