package io.smanicome.yahtzee.category;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TwoPairCategory implements Category {
    @Override
    public int evaluate(List<Integer> roll) {
        final var valueOccurrences = Objects.requireNonNull(roll).stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        final var pairs = valueOccurrences.entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .sorted((a, b) -> b.getKey().compareTo(a.getKey()))
                .limit(2)
                .map(Map.Entry::getKey)
                .toList();

        if(pairs.size() != 2) {
            return 0;
        }

        return pairs.stream()
                .reduce(0, (sum, value) -> sum + value*2);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TwoPairCategory;
    }

    @Override
    public String toString() {
        return "TwoPairCategory";
    }
}
