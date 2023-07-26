package com.lampirg.calculator.logic;

import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SimpleCalculator implements Calculator<String, String> {


    @Override
    public String calculate(String expression) {
        Operation operation = parse(expression);
        int result = switch (operation.operator()) {
            case "+" -> operation.x1() + operation.x2();
            case "-" -> operation.x1() - operation.x2();
            case "*" -> operation.x1() * operation.x2();
            case "/" -> operation.x1() / operation.x2();
            default -> throw new UnsupportedOperationException();
        };
        return String.valueOf(result);
    }

    private Operation parse(String expression) {
        expression.split("\\D");
        List<String> numbers = List.of(expression.split("\\D"));
        int x1 = Integer.parseInt(numbers.get(0));
        int x2 = Integer.parseInt(numbers.get(1));
        String operation = Arrays.stream(expression.split("\\d"))
                .filter(Predicate.not(Predicate.isEqual("")))
                .collect(Collectors.joining());
        return new Operation(x1, x2, operation);
    }

    private record Operation(int x1, int x2, String operator) {}
}
