package com.epam.composite.logic;

import com.epam.composite.logic.expression.MathExpression;
import com.epam.composite.model.Context;
import com.epam.composite.logic.expression.impl.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostfixExpressionCalculator {
    private static final String SPLITTER = "_";
    public static final int MATH_OPERATION_INDEX = 0;

    public Number calculate(String postfixExpression) {
        List<MathExpression> mathExpressions = parse(postfixExpression);

        Context context = new Context();
        for (MathExpression expression : mathExpressions) {
            expression.interpret(context);
        }

        return context.pop();
    }

    private List<MathExpression> parse(String expression) {
        List<MathExpression> mathExpressions = new ArrayList<>();
        String[] lexemes = expression.split(SPLITTER);
        for (String lexeme : lexemes) {
            char temp = lexeme.charAt(MATH_OPERATION_INDEX);

            switch (temp) {
                case '+' :
                    mathExpressions.add(new MathExpressionPlus());
                    break;
                case '-' :
                    mathExpressions.add(new MathExpressionMinus());
                    break;
                case '*' :
                    mathExpressions.add(new MathExpressionMultiply());
                    break;
                case '/' :
                    mathExpressions.add(new MathExpressionDivide());
                    break;
                default:
                    Scanner scanner = new Scanner(lexeme);
                    if (scanner.hasNextInt()) {
                        int number = scanner.nextInt();
                        mathExpressions.add(new NonTerminalExpression(number));
                    }
            }
        }

        return mathExpressions;
    }
}
