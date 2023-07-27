package com.lampirg.calculator.logic.parse.iterator;

public class IteratorWithChar extends Iterator {
    public IteratorWithChar(char sign, int index) {
        super(index);
        this.sign = sign;
    }

    private final char sign;

    public void moveIndex(String toSearch) {
        setIndex(toSearch.indexOf(sign));
    }
}
