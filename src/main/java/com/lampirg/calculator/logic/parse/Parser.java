package com.lampirg.calculator.logic.parse;

import com.lampirg.calculator.logic.expression.DoubleExpression;
import com.lampirg.calculator.logic.expression.number.*;
import com.lampirg.calculator.logic.parse.bracket.BracketFinder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiFunction;

@Service
public class Parser {

    private ExpressionParser expressionParser;
    private BracketFinder bracketFinder;

    public Parser(ExpressionParser expressionParser, BracketFinder bracketFinder) {
        this.expressionParser = expressionParser;
        this.bracketFinder = bracketFinder;
    }

    private Map<String, BiFunction<Double, Double, BinaryNumberExpression>> expressionMap = Map.of(
            "+", Sum::new,
            "-", Subtract::new,
            "*", Multiply::new,
            "/", Divide::new
    );

    public DoubleExpression parse(String inputExpression) {
        inputExpression = deBracketize(inputExpression);
        return expressionParser.parse(inputExpression);
    }

    private String deBracketize(String inputExpression) {
        while (inputExpression.contains("(")) {
            int indexOf = inputExpression.indexOf("(");
            String inBrackets = bracketFinder.findForwardStringInBrackets(inputExpression, indexOf);
            inputExpression = inputExpression.replace("(" + inBrackets + ")", parse(inBrackets).compute().toString());
        }
        return inputExpression;
    }
}
