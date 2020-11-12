package com.epam.composite.logic.expression.impl;

import com.epam.composite.model.Context;
import com.epam.composite.logic.expression.MathExpression;

public class NonTerminalExpression implements MathExpression {
    private final int number;

    public NonTerminalExpression(int number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.push(number);
    }
}
