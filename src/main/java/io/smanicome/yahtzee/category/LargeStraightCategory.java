package io.smanicome.yahtzee.category;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class LargeStraightCategory implements Category {
    private static final Set<Integer> LARGE_STRAIGHT = Set.of(2,3,4,5,6);

    @Override
    public int evaluate(List<Integer> roll) {
        return Objects.requireNonNull(roll).containsAll(LARGE_STRAIGHT) ? 20 : 0;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof LargeStraightCategory;
    }

    @Override
    public String toString() {
        return "LargeStraightCategory";
    }
}
