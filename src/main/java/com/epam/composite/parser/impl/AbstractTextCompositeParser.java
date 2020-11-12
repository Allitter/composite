package com.epam.composite.parser.impl;

import com.epam.composite.model.TextComponent;
import com.epam.composite.model.TextComposite;
import com.epam.composite.parser.TextComponentParser;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractTextCompositeParser implements TextComponentParser {
    @Override
    public TextComponent parse(String composite) {
        String subcomponentRegex = getSubcomponentRegex();
        Pattern subcomponentPattern = Pattern.compile(subcomponentRegex);
        Matcher matcher = subcomponentPattern.matcher(composite);
        List<TextComponent> subComponents = new ArrayList<>();
        while (matcher.find()) {
            String match = matcher.group();
            TextComponent component = parseSubcomponent(match);
            subComponents.add(component);
        }

        return new TextComposite(subComponents);
    }

    protected abstract String getSubcomponentRegex();
    protected abstract TextComponent parseSubcomponent(String value);
}
