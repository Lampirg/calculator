package com.lampirg.calculator.logic.parse.bracket;

import com.lampirg.calculator.logic.parse.iterator.Iterator;
import com.lampirg.calculator.logic.parse.iterator.IteratorWithChar;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

@Service
public class Bracketizer {

    private final BracketExpressionFinder bracketFinder;

    // TODO: remove hard coding
    private final Map<MoveDirection, BiFunction<String, Integer, String>> routeToBfFunction;

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
        for (ArithmeticSign sign : ArithmeticSign.values())
            input = input.replaceAll(sign.getDummy(), sign.getSign());
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
            return input.replace(it.getSign(), ArithmeticSign.getDummyBySign(it.getSign()));
        String toReplace = "(" + toBracketize + ")";
        return input.replace(toBracketize, toReplace)
                .replace(it.getSign(), ArithmeticSign.getDummyBySign(it.getSign()));
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

    @RequiredArgsConstructor
    @Getter
    private enum ArithmeticSign {
        MUL("*", "mul"),
        DIV("/", "div");
        private static final Map<String, ArithmeticSign> signToValue = Map.of(MUL.sign, MUL, DIV.sign, DIV);
        private final String sign;
        private final String dummy;
        public static String getDummyBySign(String sign) {
            return signToValue.get(sign).dummy;
        }
    }
}
