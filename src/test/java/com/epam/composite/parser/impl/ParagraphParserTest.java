package com.epam.composite.parser.impl;

import com.epam.composite.model.TextComponent;
import com.epam.composite.model.TextComposite;
import com.epam.composite.model.TextLeaf;
import com.epam.composite.parser.TextComponentParser;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.Arrays;

public class ParagraphParserTest {
    private static final String FIRST_LEXEME_STRING = "First.";
    private static final String SECOND_LEXEME_STRING = "Second.";
    private static final TextLeaf FIRST_LEXEME = TextLeaf.lexeme("First");
    private static final TextLeaf SECOND_LEXEME = TextLeaf.lexeme("Second");
    private static final TextComposite PARAGRAPH = new TextComposite(Arrays.asList(FIRST_LEXEME, SECOND_LEXEME));
    private static final String PARAGRAPH_STRING = "First.Second.\n";

    @Test
    public void testParseShouldParseTextWhenInputIsCorrect() {
        TextComponentParser subParser = Mockito.mock(TextComponentParser.class);
        Mockito.when(subParser.parse(FIRST_LEXEME_STRING)).thenReturn(FIRST_LEXEME);
        Mockito.when(subParser.parse(SECOND_LEXEME_STRING)).thenReturn(SECOND_LEXEME);
        ParagraphParser parser = new ParagraphParser(subParser);

        TextComponent actual = parser.parse(PARAGRAPH_STRING);

        Assert.assertEquals(PARAGRAPH, actual);
    }
}