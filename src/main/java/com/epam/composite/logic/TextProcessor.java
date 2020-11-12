package com.epam.composite.logic;

import com.epam.composite.model.LeafType;
import com.epam.composite.model.TextComponent;
import com.epam.composite.model.TextComposite;
import com.epam.composite.model.TextLeaf;
import java.util.*;
import java.util.function.UnaryOperator;

public class TextProcessor {
    private final PostfixExpressionCalculator expressionCalculator;

    public TextProcessor() {
        expressionCalculator = new PostfixExpressionCalculator();
    }

    public String restore(TextComponent component) {
        StringBuilder result = new StringBuilder();
        for (TextComponent paragraph : component.getChildren()) {
            result.append("\t");
            result.append(paragraph);
            result.append("\n");
        }
        return result.toString();
    }

    public TextComponent sortParagraphs(TextComponent text) {
        List<TextComponent> paragraphs = text.getChildren();
        paragraphs.sort(Comparator.comparing(paragraph -> {
            List<TextComponent> sentences = paragraph.getChildren();
            return sentences.size();
        }));

        return new TextComposite(paragraphs);
    }

    public TextComponent calculate(TextComponent text) {
        UnaryOperator<TextComponent> operator = sentence -> {
            List<TextComponent> processedLexemes = new ArrayList<>();
            for (TextComponent lexeme : sentence.getChildren()) {
                TextLeaf leaf = (TextLeaf)lexeme;
                if (leaf.is(LeafType.EXPRESSION)) {
                    Number countedExpression = processExpression(leaf);
                    String value = countedExpression.toString();
                    TextComponent result = TextLeaf.lexeme(value);
                    processedLexemes.add(result);
                } else {
                    processedLexemes.add(lexeme);
                }
            }

            return new TextComposite(processedLexemes);
        };

        return processSentences(text, operator);
    }

    public TextComponent sortLexemes(TextComponent text) {
        UnaryOperator<TextComponent> operator = sentence -> {
            List<TextComponent> lexemes = sentence.getChildren();

            lexemes.sort(Comparator.comparing(lexeme -> {
                String lexemeValue = lexeme.toString();
                lexemeValue = lexemeValue.trim();
                return lexemeValue.length();
            }));

            return new TextComposite(lexemes);
        };

        return processSentences(text, operator);
    }

    private TextComponent processSentences(TextComponent text, UnaryOperator<TextComponent> operator) {
        List<TextComponent> sentences;
        List<TextComponent> paragraphs = new ArrayList<>();
        for (TextComponent paragraph : text.getChildren()) {
            sentences = new ArrayList<>();
            for (TextComponent sentence : paragraph.getChildren()) {
                TextComponent newSentence = operator.apply(sentence);
                sentences.add(newSentence);
            }
            paragraphs.add(new TextComposite(sentences));
        }

        return new TextComposite(paragraphs);
    }

    private Number processExpression(TextComponent expression) {
        String expressionValue = expression.toString();
        expressionValue = expressionValue.trim();

        return expressionCalculator.calculate(expressionValue);
    }
}
