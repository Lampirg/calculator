package com.lampirg.calculator.logic.parse;

import com.lampirg.calculator.logic.SimpleCalculator;
import com.lampirg.calculator.logic.expression.DoubleExpression;
import com.lampirg.calculator.logic.expression.number.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

@Service
public class Parser {

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
        List<Double> numbers = new ArrayList<>();
        Optional.ofNullable(previousExpression).ifPresent(doubleExpression -> numbers.add(doubleExpression.compute()));
        Optional<String> operator = Optional.empty();
        for (; it.getIndex() < expression.length() && numbers.size() < 2; it.increment()) {
            if (isDigitOrLeadingNegative(expression, it.getIndex())) {
                numbers.add(parseNumber(expression, it));
                continue;
            }
            if (isNotDigit(expression.charAt(it.getIndex())))
                operator = Optional.of(String.valueOf(expression.charAt(it.getIndex())));
        }
        return expressionMap.get(operator.orElseThrow()).apply(numbers.get(0), numbers.get(1));
    }

    private boolean isDigitOrLeadingNegative(String expression, int i) {
        return Character.isDigit(expression.charAt(i)) || (expression.charAt(i) == '-' && i == 0);
    }

    private boolean isNotDigit(char ch) {
        return !Character.isDigit(ch);
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

    private boolean isDigitOrComa(String expression, int j) {
        return Character.isDigit(expression.charAt(j)) || expression.charAt(j) == '.';
    }

    @AllArgsConstructor
    @Getter
    @Setter
    private static class Iterator {
        private int index;

        public void increment() {
            addToIndex(1);
        }

        public void addToIndex(int toAdd) {
            index += toAdd;
        }
    }
}
