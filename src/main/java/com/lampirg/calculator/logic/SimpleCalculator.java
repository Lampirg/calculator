package com.lampirg.calculator.logic;

import com.lampirg.calculator.logic.expression.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.BiFunction;

public class SimpleCalculator implements Calculator<String, String> {

    private Map<String, BiFunction<Double, Double, BinaryNumberExpression>> expressionMap = Map.of(
            "+", Sum::new,
            "-", Subtract::new,
            "*", Multiply::new,
            "/", Divide::new
    );

    @Override
    public String calculate(String inputExpression) {
        DoubleExpression expression = parse(inputExpression);
        double result = expression.compute();
        return DecimalFormat.getNumberInstance(Locale.UK).format(result);
    }

    private DoubleExpression parse(String expression) {
        List<Double> numbers = new ArrayList<>();
        Optional<String> operator = Optional.empty();
        for (Iterator it = new Iterator(0); it.getIndex() < expression.length(); it.increment()) {
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
