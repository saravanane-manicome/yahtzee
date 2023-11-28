package io.smanicome.yahtzee.category;

import java.util.List;
import java.util.Objects;

public class ChanceCategory implements Category {
    @Override
    public int evaluate(List<Integer> roll) {
        return Objects.requireNonNull(roll).stream()
                .reduce(Integer::sum)
                .orElse(0);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ChanceCategory;
    }

    @Override
    public String toString() {
        return "ChanceCategory";
    }
}
