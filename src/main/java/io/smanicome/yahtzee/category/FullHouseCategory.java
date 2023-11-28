package io.smanicome.yahtzee.category;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FullHouseCategory implements Category {
    @Override
    public int evaluate(List<Integer> roll) {
        final var valueOccurrences = Objects.requireNonNull(roll).stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        if (valueOccurrences.size() != 2)
            return 0;

        return Math.toIntExact(valueOccurrences.entrySet()
                .stream()
                .map(entry -> entry.getKey() * entry.getValue())
                .reduce(Long::sum)
                .orElseThrow());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FullHouseCategory;
    }

    @Override
    public String toString() {
        return "FullHouseCategory";
    }
}
