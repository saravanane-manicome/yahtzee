package io.smanicome.yahtzee.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ChanceCategory should")
class ChanceCategoryShould {
    private final ChanceCategory chanceCategory = new ChanceCategory();

    public static Stream<Arguments> evaluate() {
        return Stream.of(
                Arguments.of(List.of(), 0),
                Arguments.of(List.of(1), 1),
                Arguments.of(List.of(1,2,3,4,5,6), 21)
        );
    }

    @ParameterizedTest(name = "given {0}, expect {1}")
    @DisplayName("evaluate roll as the sum of all values")
    @MethodSource("evaluate")
    void evaluate(List<Integer> roll, int expectedResult) {
        final var result = chanceCategory.evaluate(roll);
        assertEquals(expectedResult, result);
    }
}