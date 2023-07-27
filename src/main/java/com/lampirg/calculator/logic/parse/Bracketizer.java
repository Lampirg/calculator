package com.lampirg.calculator.logic.parse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class Bracketizer {

    public String bracketize(String inputString) {
        CharIndex mulIndex = new CharIndex('*', inputString.indexOf('*'));
        CharIndex divIndex = new CharIndex('/', inputString.indexOf('/'));
        while (mulIndex.getIndex() != -1 || divIndex.getIndex() != -1) {
            CharIndex cur;
            if (mulIndex.getIndex() < divIndex.getIndex() && mulIndex.getIndex() != -1)
                cur = mulIndex;
            else
                cur = divIndex;
            inputString = putBrackets(inputString, cur);
            cur.moveIndex(inputString);
        }
        throw new UnsupportedOperationException();
    }

    private String putBrackets(String inputString, CharIndex cur) {

    }

    @AllArgsConstructor
    @Getter
    @Setter
    private static class CharIndex {
        private final char sign;
        private int index;

        public void moveIndex(String toSearch) {
            index = toSearch.indexOf(sign, index);
        }
    }
}
