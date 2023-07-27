package com.lampirg.calculator.logic.parse.iterator;

import lombok.Getter;

@Getter
public class IteratorWithChar extends Iterator {
    public IteratorWithChar(String sign, int index) {
        super(index);
        this.sign = sign;
    }

    private final String sign;

    public void moveIndex(String toSearch) {
        setIndex(toSearch.indexOf(sign));
    }
}
