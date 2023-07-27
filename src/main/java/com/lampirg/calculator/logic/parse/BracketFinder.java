package com.lampirg.calculator.logic.parse;

import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.function.UnaryOperator;

@Service
public class BracketFinder {

    private Map<Character, Character> opposite = Map.of(
            '(', ')',
            ')', '('
    );

    public String findForwardStringInBrackets(String inputExpression, int startIndex) {
        int i = findOtherSide(inputExpression, startIndex, x -> x + 1);
        return inputExpression.substring(startIndex + 1, i - 1);
    }

    public String findBackwardsStringInBrackets(String inputExpression, int endIndex) {
        int i = findOtherSide(inputExpression, endIndex, x -> x - 1);
        return inputExpression.substring(i + 2, endIndex);
    }

    private int findOtherSide(String inputExpression, int startIndex, UnaryOperator<Integer> operator) {
        Deque<Character> stack = new ArrayDeque<>();
        stack.addFirst(inputExpression.charAt(startIndex));
        int i = operator.apply(startIndex);
        for (; !stack.isEmpty(); i = operator.apply(i)) {
            if (inputExpression.charAt(i) == stack.getFirst()) {
                stack.push(inputExpression.charAt(i));
                continue;
            }
            if (inputExpression.charAt(i) == opposite.get(stack.getFirst())) {
                stack.pop();
            }
        }
        return i;
    }
}
