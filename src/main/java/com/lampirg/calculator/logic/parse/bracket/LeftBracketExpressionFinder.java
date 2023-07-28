package com.lampirg.calculator.logic.parse.bracket;

public interface LeftBracketExpressionFinder {
    String findBackwardsStringInBrackets(String inputExpression, int endIndex);
}
