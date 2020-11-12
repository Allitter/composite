package com.epam.composite.logic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PostfixExpressionCalculatorTest {
    private static final String EXPRESSION = "4_5_8_+_-";
    public static final int EXPRESSION_RESULT = -9;

    @Test
    public void testCalculateShouldReturnResultWhenInputIsCorrect() {
        PostfixExpressionCalculator calculator = new PostfixExpressionCalculator();

        Number actual = calculator.calculate(EXPRESSION);

        Assert.assertEquals(EXPRESSION_RESULT, actual);
    }
}