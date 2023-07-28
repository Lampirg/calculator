package com.lampirg.calculator.unit;

import com.lampirg.calculator.logic.parse.bracket.BracketFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBracketFinder {
    
    private BracketFinder bracketFinder = new BracketFinder();

    @Test
    void testGoingForward() {
        Assertions.assertEquals("(11)+5", bracketFinder.findForwardStringInBrackets("((11)+5)", 0));
        Assertions.assertEquals("11", bracketFinder.findForwardStringInBrackets("((11)+5)", 1));
    }

    @Test
    void testGoingBackwards() {
        Assertions.assertEquals("(11)+5", bracketFinder.findBackwardsStringInBrackets("((11)+5)", 7));
        Assertions.assertEquals("11", bracketFinder.findBackwardsStringInBrackets("((11)+5)", 4));
    }
}
