package com.epam.composite.parser.impl;

import com.epam.composite.model.TextComponent;
import com.epam.composite.parser.TextComponentParser;

public class TextParser extends AbstractTextCompositeParser {
    private static final String PARAGRAPH_REGEX = "\t.+\n";
    private final TextComponentParser parser;

    public TextParser(){
        parser = new ParagraphParser();
    }

    /*package private for test*/
    TextParser(TextComponentParser parser) {
        this.parser = parser;
    }

    @Override
    protected String getSubcomponentRegex() {
        return PARAGRAPH_REGEX;
    }

    @Override
    protected TextComponent parseSubcomponent(String paragraph) {
        return parser.parse(paragraph);
    }
}
