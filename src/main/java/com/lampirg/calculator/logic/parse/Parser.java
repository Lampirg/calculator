package com.lampirg.calculator.logic.parse;

import com.lampirg.calculator.logic.expression.DoubleExpression;
import com.lampirg.calculator.logic.expression.number.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiFunction;

@Service
public class Parser {

    private ExpressionParser expressionParser;

    public Parser(ExpressionParser expressionParser) {
        this.expressionParser = expressionParser;
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

    public String deBracketize(String inputExpression) {
        int indexOf = inputExpression.indexOf("(");
        if (indexOf == -1)
            return inputExpression;
        String inBrackets = findStringInBrackets(inputExpression, indexOf);
        return inputExpression.replace(inBrackets, parse(inBrackets).compute().toString());
    }

    private String findStringInBrackets(String inputExpression, int startIndex) {
        Deque<Character> stack = new ArrayDeque<>();
        stack.addFirst(inputExpression.charAt(startIndex));
        int i = startIndex + 1;
        for (; !stack.isEmpty(); i++) {
            if (inputExpression.charAt(i) == '(') {
                stack.push(inputExpression.charAt(i));
                continue;
            }
            if (inputExpression.charAt(i) == ')') {
                stack.pop();
            }
        }
        return inputExpression.substring(startIndex + 1, i - 1);
    }
}
