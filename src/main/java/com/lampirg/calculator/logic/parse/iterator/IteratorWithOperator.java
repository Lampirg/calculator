package com.lampirg.calculator.logic.parse.iterator;

import java.util.ArrayDeque;
import java.util.Deque;

public class IteratorWithOperator extends Iterator {

    private Deque<String> operatorStack = new ArrayDeque<>();

    public IteratorWithOperator(int index) {
        super(index);
    }

    public void pushOperator(String operator) {
        if (!operatorStack.isEmpty())
            throw new IllegalStateException("Pushing second operator");
        operatorStack.push(operator);
    }

    public String popOperator() {
        return operatorStack.pop();
    }

    public String peekOperator() {
        return operatorStack.peekFirst();
    }
}
