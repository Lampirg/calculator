package com.lampirg.calculator.logic.parse;

import com.lampirg.calculator.logic.expression.DoubleExpression;
import com.lampirg.calculator.logic.parse.bracket.DeBracketizer;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class Parser {

    private DeBracketizer deBracketizer;
    private ExpressionParser expressionParser;

    @Autowired
    public void setDeBracketizer(DeBracketizer deBracketizer) {
        this.deBracketizer = deBracketizer;
    }
    @Autowired
    public void setExpressionParser(ExpressionParser expressionParser) {
        this.expressionParser = expressionParser;
    }

    public DoubleExpression parse(String inputExpression) {
        inputExpression = deBracketizer.deBracketize(inputExpression);
        return expressionParser.parse(inputExpression);
    }
}
