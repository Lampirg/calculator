package com.lampirg.calculator.logic.parse.bracket;

import com.lampirg.calculator.logic.parse.iterator.Iterator;
import com.lampirg.calculator.logic.parse.iterator.IteratorWithChar;
import org.springframework.stereotype.Service;

@Service
public class Bracketizer {

    public String bracketize(String inputString) {
        IteratorWithChar mulIt = new IteratorWithChar('*', inputString.indexOf('*'));
        IteratorWithChar divIt = new IteratorWithChar('/', inputString.indexOf('/'));
        while (mulIt.getIndex() != -1 || divIt.getIndex() != -1) {
            IteratorWithChar it;
            if (firstIsCloserToStart(mulIt, divIt))
                it = mulIt;
            else
                it = divIt;
            inputString = putBrackets(inputString, it);
            mulIt.moveIndex(inputString);
            divIt.moveIndex(inputString);
        }
        return inputString;
    }

    private boolean firstIsCloserToStart(Iterator first, Iterator second) {
        return (first.getIndex() < second.getIndex() || second.getIndex() == -1) && first.getIndex() != -1;
    }

    private String putBrackets(String inputString, IteratorWithChar cur) {

        throw new UnsupportedOperationException();
    }

//    private double parseNumber(String expression, Iterator it) {
//        int sign = 1;
//        if (expression.charAt(it.getIndex()) == '-') {
//            sign = -1;
//            it.increment();
//        }
//        int j = it.getIndex();
//        while (j < expression.length() && isDigitOrComa(expression, j))
//            j++;
//        double number = Double.parseDouble(expression.substring(it.getIndex(), j)) * sign;
//        it.setIndex(j - 1);
//        return number;
//    }
}
