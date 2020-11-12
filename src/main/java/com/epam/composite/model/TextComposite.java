package com.epam.composite.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextComposite implements TextComponent{
    private final List<TextComponent> components;

    public TextComposite(List<TextComponent> components) {
        this.components = components;
    }

    @Override
    public List<TextComponent> getChildren() {
        return new ArrayList<>(components);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (TextComponent component : components) {
            stringBuilder.append(component);
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TextComposite that = (TextComposite) o;
        return Objects.equals(components, that.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(components);
    }
}
