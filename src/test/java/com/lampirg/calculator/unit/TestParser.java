package com.lampirg.calculator.unit;

import com.lampirg.calculator.logic.expression.number.Multiply;
import com.lampirg.calculator.logic.expression.number.Sum;
import com.lampirg.calculator.logic.parse.ExpressionParser;
import com.lampirg.calculator.logic.parse.Parser;
import com.lampirg.calculator.logic.parse.bracket.DeBracketizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test parser")
public class TestParser {

    @Mock
    private ExpressionParser expressionParser;
    @Mock
    private DeBracketizer deBracketizer;
    @InjectMocks
    private Parser parser;

    @Test
    @DisplayName("(12)+(24)")
    void sumGivenSimpleBrackets() {
        String inputExpression = "(12)+(24)";
        Mockito.doReturn("12+24").when(deBracketizer).deBracketize(inputExpression);
        Mockito.doReturn(new Sum(12, 24)).when(expressionParser).parse("12+24");
        Assertions.assertEquals(new Sum(12, 24), parser.parse(inputExpression));
    }

    @Test
    @DisplayName("(12)*((24))")
    void mulGivenMultipleSimpleBrackets() {
        String inputExpression = "(12)*((24))";
        Mockito.doReturn("12*24").when(deBracketizer).deBracketize(inputExpression);
        Mockito.doReturn(new Multiply(12, 24)).when(expressionParser).parse("12*24");
        Assertions.assertEquals(new Multiply(12, 24), parser.parse(inputExpression));
    }
}
