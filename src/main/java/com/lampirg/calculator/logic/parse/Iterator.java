package com.lampirg.calculator.logic.parse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Iterator {
    private int index;

    public void increment() {
        addToIndex(1);
    }

    public void addToIndex(int toAdd) {
        index += toAdd;
    }
}
