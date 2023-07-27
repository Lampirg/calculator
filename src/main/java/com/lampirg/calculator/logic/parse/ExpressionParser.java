package com.lampirg.calculator.logic.parse;

import com.lampirg.calculator.logic.expression.DoubleExpression;
import com.lampirg.calculator.logic.expression.number.*;
import com.lampirg.calculator.logic.parse.iterator.Iterator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

@Service
public class ExpressionParser {

    private Map<String, BiFunction<Double, Double, BinaryNumberExpression>> expressionMap = Map.of(
            "+", Sum::new,
            "-", Subtract::new,
            "*", Multiply::new,
            "/", Divide::new
    );

    public DoubleExpression parse(String inputExpression) {
        Iterator it = new Iterator(0);
        DoubleExpression expression = firstParseBinaryExpression(inputExpression, it);
        while (it.getIndex() < inputExpression.length()) {
            expression = parseBinaryExpression(expression, inputExpression, it);
        }
        return expression;
    }

    private DoubleExpression firstParseBinaryExpression(String expression, Iterator it) {
        return parseBinaryExpression(null, expression, it);
    }

    private DoubleExpression parseBinaryExpression(DoubleExpression previousExpression, String expression, Iterator it) {
        List<Double> numbers = initializeNumberList(previousExpression);
        for (; it.getIndex() < expression.length() && numbers.size() < 2; it.increment()) {
            if (isDigitOrLeadingNegative(expression, it)) {
                numbers.add(parseNumber(expression, it));
                continue;
            }
            if (isOperator(expression.charAt(it.getIndex())))
                it.pushOperator(String.valueOf(expression.charAt(it.getIndex())));
        }
        if (numbers.size() == 1)
            return new NumberExpression(numbers.get(0));
        return expressionMap.get(it.popOperator()).apply(numbers.get(0), numbers.get(1));
    }

    private List<Double> initializeNumberList(DoubleExpression previousExpression) {
        List<Double> numbers = new ArrayList<>();
        Optional.ofNullable(previousExpression).ifPresent(doubleExpression -> numbers.add(doubleExpression.compute()));
        return numbers;
    }

    private boolean isDigitOrLeadingNegative(String expression, Iterator it) {
        int i = it.getIndex();
        String op = it.peekOperator();
        return Character.isDigit(expression.charAt(i)) || (expression.charAt(i) == '-' && (i == 0 || op != null));
    }

    private double parseNumber(String expression, Iterator it) {
        int sign = 1;
        if (expression.charAt(it.getIndex()) == '-') {
            sign = -1;
            it.increment();
        }
        int j = it.getIndex();
        while (j < expression.length() && isDigitOrComa(expression, j))
            j++;
        double number = Double.parseDouble(expression.substring(it.getIndex(), j)) * sign;
        it.setIndex(j - 1);
        return number;
    }

    private boolean isOperator(char ch) {
        return !Character.isDigit(ch);
    }

    private boolean isDigitOrComa(String expression, int j) {
        return Character.isDigit(expression.charAt(j)) || expression.charAt(j) == '.';
    }
}
