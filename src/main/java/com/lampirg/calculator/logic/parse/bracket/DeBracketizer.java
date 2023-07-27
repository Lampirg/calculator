package com.lampirg.calculator.logic.parse.bracket;

import com.lampirg.calculator.logic.parse.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeBracketizer {

    private RightBracketExpressionFinder bracketFinder;
    private Parser parser;

    @Autowired
    public void setParser(Parser parser) {
        this.parser = parser;
    }

    @Autowired
    public void setBracketFinder(RightBracketExpressionFinder bracketFinder) {
        this.bracketFinder = bracketFinder;
    }

    public String deBracketize(String inputExpression) {
        while (inputExpression.contains("(")) {
            int indexOf = inputExpression.indexOf("(");
            String inBrackets = bracketFinder.findForwardStringInBrackets(inputExpression, indexOf);
            inputExpression = inputExpression.replace("(" + inBrackets + ")", parser.parse(inBrackets).compute().toString());
        }
        return inputExpression;
    }
}
