package com.epam.composite.parser;

import com.epam.composite.model.TextComponent;

public interface TextComponentParser {
    TextComponent parse(String value);
}
