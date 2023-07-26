package com.lampirg.calculator.logic;

import com.lampirg.calculator.logic.expression.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

public class SimpleCalculator implements Calculator<String, String> {

    private Map<String, BiFunction<Integer, Integer, BinaryExpression>> expressionMap = Map.of(
            "+", Sum::new,
            "-", Subtract::new,
            "*", Multiply::new,
            "/", Divide::new
    );

    @Override
    public String calculate(String inputExpression) {
        BinaryExpression expression = parse(inputExpression);
        int result = expression.compute();
        return String.valueOf(result);
    }

    private BinaryExpression parse(String expression) {
        List<Integer> numbers = new ArrayList<>();
        String operator = null;
        for (int i = 0; i < expression.length(); i++) {
            if (isDigit(expression, i)) {
                numbers.add(parseNumber(expression, i));
                i += numbers.get(numbers.size() - 1).toString().length() - 1;
                continue;
            }
            if (isNotDigit(expression.charAt(i)))
                operator = String.valueOf(expression.charAt(i));
        }
        return expressionMap.get(operator).apply(numbers.get(0), numbers.get(1));
    }

    private boolean isDigit(String expression, int i) {
        return Character.isDigit(expression.charAt(i)) || (expression.charAt(i) == '-' && i == 0);
    }

    private boolean isNotDigit(char ch) {
        return !Character.isDigit(ch);
    }

    private int parseNumber(String expression, int i) {
        int sign = 1;
        if (expression.charAt(i) == '-') {
            sign = -1;
            i++;
        }
        int j = i;
        while (j < expression.length() && Character.isDigit(expression.charAt(j)))
            j++;
        return Integer.parseInt(expression.substring(i, j)) * sign;
    }

    private record Operation(int x1, int x2, String operator) {
    }
}
