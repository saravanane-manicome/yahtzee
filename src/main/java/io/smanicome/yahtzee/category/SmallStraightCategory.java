package io.smanicome.yahtzee.category;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SmallStraightCategory implements Category {
    private static final Set<Integer> SMALL_STRAIGHT = Set.of(1,2,3,4,5);

    @Override
    public int evaluate(List<Integer> roll) {
        return Objects.requireNonNull(roll).containsAll(SMALL_STRAIGHT) ? 15 : 0;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SmallStraightCategory;
    }

    @Override
    public String toString() {
        return "SmallStraightCategory";
    }
}
