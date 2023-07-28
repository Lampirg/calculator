package com.lampirg.calculator.logic.parse.bracket;

import com.lampirg.calculator.logic.parse.iterator.Iterator;
import com.lampirg.calculator.logic.parse.iterator.IteratorWithChar;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

@Service
public class Bracketizer {

    private final BracketExpressionFinder bracketFinder;

    private static final UnaryOperator<Integer> forward = x -> x + 1;
    private static final UnaryOperator<Integer> backwards = x -> x - 1;

    // TODO: remove hard coding
    private final Map<MoveDirection, BiFunction<String, Integer, String>> routeToBfFunction;

    private final static Map<String, String> dummies = Map.of(
            "*", "mul",
            "/", "div"
    );

    public Bracketizer(BracketExpressionFinder bracketFinder) {
        this.bracketFinder = bracketFinder;
        routeToBfFunction = Map.of(
                MoveDirection.FORWARD, bracketFinder::findForwardStringInBrackets,
                MoveDirection.BACKWARDS, bracketFinder::findBackwardsStringInBrackets
        );
    }

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
        int rightEnd = findEnd(input, it, MoveDirection.FORWARD);
        int leftEnd = findEnd(input, it, MoveDirection.BACKWARDS);
        String toBracketize = input.substring(leftEnd + 1, rightEnd);
        if (alreadyBracketized(input, toBracketize, leftEnd + 1))
            return input.replace(it.getSign(), dummies.get(it.getSign()));
        String toReplace = "(" + toBracketize + ")";
        return input.replace(toBracketize, toReplace)
                .replace(it.getSign(), dummies.get(it.getSign()));
    }

    // TODO: this method is based on parseInt method in ExpressionParser class so they might be applicable to refactor
    private int findEnd(String input, Iterator it, MoveDirection direction) {
        int j = direction.apply(it.getIndex());
        if (input.charAt(j) == direction.getBoundedBracket())
            return j + direction.apply(0) * (routeToBfFunction.get(direction).apply(input, j).length() + 1);
        while (j >= 0 && j < input.length() && (isDigitDotOrBracket(input, j) || isUnaryMinus(input, j, direction)))
            j = direction.apply(j);
        return j;
    }

    private boolean isDigitDotOrBracket(String input, int j) {
        return Character.isDigit(input.charAt(j)) || input.charAt(j) == '.' || input.charAt(j) == '(' || input.charAt(j) == ')';
    }

    private boolean isUnaryMinus(String expression, int j, MoveDirection direction) {
        return expression.charAt(j) == '-' && (j == 0 || isNotDigitOrBracket(expression, j, direction));
    }

    private boolean isNotDigitOrBracket(String expression, int j, MoveDirection direction) {
        return !(Character.isDigit(expression.charAt(direction.apply(j))) || expression.charAt(direction.apply(j)) == direction.getBoundedBracket());
    }

    private boolean alreadyBracketized(String input, String toBracketize, int leftEnd) {
        return input.charAt(leftEnd) == '(' &&
                bracketFinder.findForwardStringInBrackets(input, leftEnd).equals(toBracketize.substring(1, toBracketize.length() - 1));
    }

    @RequiredArgsConstructor
    @Getter
    private enum MoveDirection {
        FORWARD(x -> x + 1, '('),
        BACKWARDS(x -> x - 1, ')');
        private final UnaryOperator<Integer> function;
        private final char boundedBracket;

        public int apply(int val) {
            return function.apply(val);
        }
    }
}
