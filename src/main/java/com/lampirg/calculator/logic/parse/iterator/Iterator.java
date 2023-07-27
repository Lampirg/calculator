package com.lampirg.calculator.logic.parse.iterator;

import java.util.ArrayDeque;
import java.util.Deque;

public class Iterator {
    private int index;
    private Deque<String> operatorStack = new ArrayDeque<>();

    public Iterator(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void increment() {
        addToIndex(1);
    }

    public void decrement() {
        addToIndex(-1);
    }

    public void addToIndex(int toAdd) {
        index += toAdd;
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
