package com.lampirg.calculator.logic.parse.bracket;

import com.lampirg.calculator.logic.parse.iterator.Iterator;
import com.lampirg.calculator.logic.parse.iterator.IteratorWithChar;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.UnaryOperator;

@Service
public class Bracketizer {

    private static Map<UnaryOperator<Integer>, Character> operatorToBracket = Map.of(
            x -> x + 1, '(',
            x -> x - 1, ')'
    );

    private static Map<String, String> dummies = Map.of(
            "*", "mul",
            "/", "div"
    );

    public String bracketize(String input) {
        IteratorWithChar mulIt = new IteratorWithChar("*", input.indexOf("*"));
        IteratorWithChar divIt = new IteratorWithChar("/", input.indexOf("/"));
        while (mulIt.getIndex() != -1 || divIt.getIndex() != -1) {
            IteratorWithChar it;
            if (firstIsCloserToStart(mulIt, divIt))
                it = mulIt;
            else
                it = divIt;
            input = putBrackets(input, it);
            mulIt.moveIndex(input);
            divIt.moveIndex(input);
        }
        for (Map.Entry<String, String> entry : dummies.entrySet())
            input = input.replaceAll(entry.getValue(), entry.getKey());
        return input;
    }

    private boolean firstIsCloserToStart(Iterator first, Iterator second) {
        return (first.getIndex() < second.getIndex() || second.getIndex() == -1) && first.getIndex() != -1;
    }

    private String putBrackets(String input, IteratorWithChar it) {
        int rightEnd = findEnd(input, it, x -> x + 1);
        int leftEnd = findEnd(input, it, x -> x - 1);
        String toReplace = "(" + input.substring(leftEnd + 1, rightEnd) + ")";
        return input.replace(input.substring(leftEnd + 1, rightEnd), toReplace)
                .replace(it.getSign(), dummies.get(it.getSign()));
    }

    // TODO: this method is similar to parseInt in ExpressionParser class so they should be refactored
    private int findEnd(String input, Iterator it, UnaryOperator<Integer> operator) {
        int j = operator.apply(it.getIndex());
        while (j > 0 && j < input.length() && (isDigitOrDot(input, j) || isUnaryMinus(input, j, operator)))
            j = operator.apply(j);
        return j;
    }

    private boolean isDigitOrDot(String expression, int j) {
        return Character.isDigit(expression.charAt(j)) || expression.charAt(j) == '.';
    }

    private boolean isUnaryMinus(String expression, int j, UnaryOperator<Integer> op) {
        return expression.charAt(j) == '-' && !(Character.isDigit(expression.charAt(op.apply(j))) || expression.charAt(op.apply(j)) == operatorToBracket.get(op));
    }
}
