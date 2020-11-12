package com.epam.composite.logic.expression.impl;

import com.epam.composite.model.Context;
import org.junit.Assert;
import org.junit.Test;

public class NonTerminalExpressionTest {
    private static final int FIRST_NUMBER = 6;

    @Test
    public void testInterpretShouldMultiplyWhenInputIsCorrect() {
        NonTerminalExpression mathExpression = new NonTerminalExpression(FIRST_NUMBER);
        Context context = new Context();

        mathExpression.interpret(context);

        Context expected = new Context();
        expected.push(FIRST_NUMBER);
        Assert.assertEquals(expected, context);
    }
}