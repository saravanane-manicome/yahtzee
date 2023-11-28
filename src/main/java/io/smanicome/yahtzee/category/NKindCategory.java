package io.smanicome.yahtzee.category;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NKindCategory implements Category {
    private final int size;

    public NKindCategory(int size) {
        this.size = size;
    }

    @Override
    public int evaluate(List<Integer> roll) {
        final var valueOccurrences = Objects.requireNonNull(roll).stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return Math.toIntExact(valueOccurrences.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == size)
                .max(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() * entry.getValue())
                .orElse(0L));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NKindCategory that = (NKindCategory) o;
        return size == that.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }

    @Override
    public String toString() {
        return "NKindCategory{" +
                "size=" + size +
                '}';
    }
}
