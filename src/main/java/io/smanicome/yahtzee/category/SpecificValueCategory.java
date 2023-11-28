package io.smanicome.yahtzee.category;

import java.util.List;
import java.util.Objects;

public class SpecificValueCategory implements Category {
    private final int value;

    public SpecificValueCategory(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(List<Integer> roll) {
        return Objects.requireNonNull(roll).stream()
                .filter(i -> i.equals(value))
                .reduce(Integer::sum)
                .orElse(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificValueCategory that = (SpecificValueCategory) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "SpecificValueCategory{" +
                "value=" + value +
                '}';
    }
}
