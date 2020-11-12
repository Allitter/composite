package com.epam.composite.parser.impl;

import com.epam.composite.model.TextComponent;
import com.epam.composite.parser.TextComponentParser;

public class ParagraphParser extends AbstractTextCompositeParser {
    private static final String SENTENCE_REGEX = "([^.?!]+)(([.?!]+) ?)";
    private final TextComponentParser parser;

    public ParagraphParser() {
        parser = new SentenceParser();
    }

    /*package private for test*/
    ParagraphParser(TextComponentParser parser) {
        this.parser = parser;
    }

    @Override
    protected String getSubcomponentRegex() {
        return SENTENCE_REGEX;
    }

    @Override
    protected TextComponent parseSubcomponent(String sentence) {
        return parser.parse(sentence);
    }
}
