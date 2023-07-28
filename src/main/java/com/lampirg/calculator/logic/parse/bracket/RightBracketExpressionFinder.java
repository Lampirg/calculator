package com.lampirg.calculator.logic.parse.bracket;

public interface RightBracketExpressionFinder {
    String findForwardStringInBrackets(String inputExpression, int indexOf);
}
