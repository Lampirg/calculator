package com.lampirg.calculator.logic;

import com.lampirg.calculator.logic.expression.*;
import com.lampirg.calculator.logic.expression.number.*;
import com.lampirg.calculator.logic.parse.Parser;
import com.lampirg.calculator.logic.parse.bracket.Bracketizer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class SimpleCalculator implements Calculator<String, String> {

    private final Bracketizer bracketizer;
    private final Parser parser;

    @Override
    public String calculate(String inputExpression) {
        inputExpression = bracketizer.bracketize(inputExpression);
        DoubleExpression expression = parser.parse(inputExpression);
        double result = expression.compute();
        return DecimalFormat.getNumberInstance(Locale.UK).format(result);
    }
}
