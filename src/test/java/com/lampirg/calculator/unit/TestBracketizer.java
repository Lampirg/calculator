package com.lampirg.calculator.unit;

import com.lampirg.calculator.logic.parse.Bracketizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBracketizer {

    private Bracketizer bracketizer = new Bracketizer();

    @Test
    void givenAdditionAndMultiplication() {
        Assertions.assertEquals("1+(3*4)", bracketizer.bracketize("1+3*4"));
    }
}
