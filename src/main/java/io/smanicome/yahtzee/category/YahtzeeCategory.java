package io.smanicome.yahtzee.category;

import java.util.List;
import java.util.Objects;

public class YahtzeeCategory implements Category {
    @Override
    public int evaluate(List<Integer> roll) {
        final var distinctValueCount =  Objects.requireNonNull(roll).stream()
                .distinct()
                .count();

        return distinctValueCount > 1 ? 0 : 50;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof YahtzeeCategory;
    }

    @Override
    public String toString() {
        return "YahtzeeCategory";
    }
}
