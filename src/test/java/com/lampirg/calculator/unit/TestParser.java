package com.lampirg.calculator.unit;

import com.lampirg.calculator.logic.expression.number.*;
import com.lampirg.calculator.logic.parse.ExpressionParser;
import com.lampirg.calculator.logic.parse.Parser;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.regex.Matcher;

@ExtendWith(MockitoExtension.class)
public class TestParser {

    @Mock
    private ExpressionParser expressionParser;
    @InjectMocks
    private Parser parser;

    private final Map<String, BiFunction<Double, Double, BinaryNumberExpression>> expressionMap = Map.of(
            "+", Sum::new,
            "-", Subtract::new,
            "*", Multiply::new,
            "/", Divide::new
    );
    
    @Test
    void givenSimpleBrackets() {
        // TODO: refactor this mess
        // Regex for one number "^[\\d]+[\\.][\\d]+$" (I'm done with regex so this comment should be deleted soon)
        Mockito.doReturn(new NumberExpression(12)).when(expressionParser).parse("12");
        Mockito.doReturn(new NumberExpression(24)).when(expressionParser).parse("24");
        Mockito.doReturn(new NumberExpression(25)).when(expressionParser).parse("25");
        Mockito.doReturn(new NumberExpression(11)).when(expressionParser).parse("11");
        Mockito.doReturn(new NumberExpression(15)).when(expressionParser).parse("15");
        Mockito.doReturn(new NumberExpression(10)).when(expressionParser).parse("10");
        Mockito.doReturn(new NumberExpression(125)).when(expressionParser).parse("125");
        Mockito.doReturn(new Sum(12, 24)).when(expressionParser).parse("12.0+24.0");
        Mockito.doReturn(new Subtract(25, 11)).when(expressionParser).parse("25.0-11.0");
        Mockito.doReturn(new Multiply(15, 10)).when(expressionParser).parse("15.0*10.0");
        Mockito.doReturn(new Divide(125, 25)).when(expressionParser).parse("125.0/25.0");
        Assertions.assertEquals(new Sum(12, 24), parser.parse("(12)+(24)"));
        Assertions.assertEquals(new Subtract(25, 11), parser.parse("(25)-(11)"));
        Assertions.assertEquals(new Multiply(15, 10), parser.parse("(15)*(10)"));
        Assertions.assertEquals(new Divide(125, 25), parser.parse("(125)/(25)"));
    }
}
