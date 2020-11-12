package com.epam.composite.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TextLeaf implements TextComponent {
    private final String value;
    private final LeafType type;

    private TextLeaf(String value, LeafType type) {
        this.value = value;
        this.type = type;
    }

    public static TextLeaf lexeme(String value) {
        return new TextLeaf(value, LeafType.LEXEME);
    }

    public static TextLeaf expression(String value) {
        return new TextLeaf(value, LeafType.EXPRESSION);
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }

    public boolean is(LeafType type) {
        if (type == LeafType.LEXEME) {
            return true;
        }

        return this.type == type;
    }

    @Override
    public String toString() {
        return String.format("%s ", value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TextLeaf leaf = (TextLeaf) o;
        return Objects.equals(value, leaf.value) &&
                type == leaf.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }
}
