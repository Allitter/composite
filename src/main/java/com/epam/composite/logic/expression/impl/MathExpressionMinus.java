package com.epam.composite.logic.expression.impl;

import com.epam.composite.model.Context;
import com.epam.composite.logic.expression.MathExpression;

public class MathExpressionMinus implements MathExpression {
    @Override
    public void interpret(Context context) {
        int firstNumber = context.pop();
        int secondNumber = context.pop();
        int result = secondNumber - firstNumber;
        context.push(result);
    }
}
