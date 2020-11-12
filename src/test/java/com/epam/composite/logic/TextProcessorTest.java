package com.epam.composite.logic;

import com.epam.composite.model.TextComponent;
import com.epam.composite.model.TextComposite;
import com.epam.composite.model.TextLeaf;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class TextProcessorTest {
    private static final TextLeaf FIRST_LEXEME = TextLeaf.lexeme("First");
    private static final TextLeaf SECOND_LEXEME = TextLeaf.lexeme("second,");
    private static final TextLeaf THIRD_LEXEME = TextLeaf.lexeme("third");
    private static final TextLeaf FIRST_EXPRESSION = TextLeaf.expression("<_4_5_7_+_-_>");
    private static final TextLeaf FIRST_EXPRESSION_VALUE = TextLeaf.lexeme("-8");
    private static final String TEXT_STRING =
            "\tFirst second, <_4_5_7_+_-_> second, third First First \n" +
                    "\tFirst second, <_4_5_7_+_-_> second, third First \n";
    private static final TextComponent TEXT = buildText(new TextComponent[][][] {
            {
                    { FIRST_LEXEME, SECOND_LEXEME, FIRST_EXPRESSION },
                    { SECOND_LEXEME, THIRD_LEXEME, FIRST_LEXEME },
                    { FIRST_LEXEME }
            },
            {
                    { FIRST_LEXEME, SECOND_LEXEME, FIRST_EXPRESSION },
                    { SECOND_LEXEME, THIRD_LEXEME, FIRST_LEXEME },
            }
    });

    @Test
    public void testCalculateShouldReturnTextWithCalculatedExpressions() {
        TextProcessor processor = new TextProcessor();

        TextComponent actual = processor.calculate(TEXT);

        TextComponent expected = buildText(new TextComponent[][][] {
                {
                        { FIRST_LEXEME, SECOND_LEXEME, FIRST_EXPRESSION_VALUE },
                        { SECOND_LEXEME, THIRD_LEXEME, FIRST_LEXEME },
                        { FIRST_LEXEME }
                },
                {
                        { FIRST_LEXEME, SECOND_LEXEME, FIRST_EXPRESSION_VALUE },
                        { SECOND_LEXEME, THIRD_LEXEME, FIRST_LEXEME },
                }
        });
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSortLexemesShouldReturnTextWithLexemesSortedByLength() {
        TextProcessor processor = new TextProcessor();

        TextComponent actual = processor.sortLexemes(TEXT);

        TextComponent expected = buildText(new TextComponent[][][] {
                {
                        { FIRST_LEXEME, SECOND_LEXEME, FIRST_EXPRESSION },
                        { THIRD_LEXEME, FIRST_LEXEME, SECOND_LEXEME},
                        { FIRST_LEXEME }
                },
                {
                        { FIRST_LEXEME, SECOND_LEXEME, FIRST_EXPRESSION },
                        { THIRD_LEXEME, FIRST_LEXEME, SECOND_LEXEME}
                }
        });

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSortParagraphsShouldReturnTextWithParagraphsSortedByNumberOfSentences() {
        TextProcessor processor = new TextProcessor();

        TextComponent actual = processor.sortParagraphs(TEXT);

        TextComponent expected = buildText(new TextComponent[][][] {
                {
                        { FIRST_LEXEME, SECOND_LEXEME, FIRST_EXPRESSION },
                        { SECOND_LEXEME, THIRD_LEXEME, FIRST_LEXEME },
                },
                {
                        { FIRST_LEXEME, SECOND_LEXEME, FIRST_EXPRESSION },
                        { SECOND_LEXEME, THIRD_LEXEME, FIRST_LEXEME },
                        { FIRST_LEXEME }
                }
        });
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRestoreShouldReturnRestoredTextWhenInputIsCorrect() {
        TextProcessor processor = new TextProcessor();

        String actual = processor.restore(TEXT);

        Assert.assertEquals(TEXT_STRING, actual);
    }

    private static TextComponent buildText(TextComponent[][][] components) {
        List<TextComponent> paragraphs = new ArrayList<>();
        for (TextComponent[][] component : components) {
            List<TextComponent> paragraph = new ArrayList<>();
            for (TextComponent[] textComponents : component) {
                List<TextComponent> sentence = new ArrayList<>();
                sentence.addAll(Arrays.asList(textComponents));
                paragraph.add(new TextComposite(sentence));
            }
            paragraphs.add(new TextComposite(paragraph));
        }

        return new TextComposite(paragraphs);
    }
}