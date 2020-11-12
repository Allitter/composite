package com.epam.composite.logic.expression.impl;

import com.epam.composite.model.Context;
import org.junit.Assert;
import org.junit.Test;

public class MathExpressionPlusTest {
    private static final int FIRST_NUMBER = 6;
    private static final int SECOND_NUMBER = 2;
    private static final int RESULT = 8;

    @Test
    public void testInterpretShouldAddWhenInputIsCorrect() {
        MathExpressionPlus mathExpression = new MathExpressionPlus();
        Context context = new Context();
        context.push(FIRST_NUMBER);
        context.push(SECOND_NUMBER);

        mathExpression.interpret(context);

        Context expected = new Context();
        expected.push(RESULT);
        Assert.assertEquals(expected, context);
    }
}