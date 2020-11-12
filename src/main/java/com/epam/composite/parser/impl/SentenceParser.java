package com.epam.composite.parser.impl;

import com.epam.composite.model.TextComponent;
import com.epam.composite.model.TextLeaf;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractTextCompositeParser {
    private static final String LEXEME_REGEX = "\\S+";
    private static final String MATHS_EXPRESSION_REGEX = "<.+>\\S* *?";

    @Override
    protected String getSubcomponentRegex() {
        return LEXEME_REGEX;
    }

    @Override
    protected TextComponent parseSubcomponent(String subcomponent) {
        if (subcomponent.matches(MATHS_EXPRESSION_REGEX)) {
            String expression = getMatch(subcomponent, MATHS_EXPRESSION_REGEX);
            return TextLeaf.expression(expression);
        }

        String lexeme = getMatch(subcomponent, LEXEME_REGEX);
        return TextLeaf.lexeme(lexeme);
    }

    private String getMatch(String line, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        return matcher.find() ? matcher.group() : "";
    }
}
