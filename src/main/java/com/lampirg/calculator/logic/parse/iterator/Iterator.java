package com.lampirg.calculator.logic.parse.iterator;

public class Iterator {
    private int index;

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

}
