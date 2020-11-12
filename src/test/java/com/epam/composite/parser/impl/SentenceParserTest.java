package com.epam.composite.parser.impl;

import com.epam.composite.model.TextComponent;
import com.epam.composite.model.TextComposite;
import com.epam.composite.model.TextLeaf;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

public class SentenceParserTest {
    private static final TextLeaf LEXEME = TextLeaf.lexeme("First,");
    private static final TextLeaf EXPRESSION = TextLeaf.expression("<_1_2_+_>.");
    private static final TextComposite SENTENCE = new TextComposite(Arrays.asList(LEXEME, EXPRESSION));
    private static final String SENTENCE_STRING = "First, <_1_2_+_>. ";

    @Test
    public void testParseShouldParseTextWhenInputIsCorrect() {
        SentenceParser parser = new SentenceParser();

        TextComponent actual = parser.parse(SENTENCE_STRING);

        Assert.assertEquals(SENTENCE, actual);
    }
}