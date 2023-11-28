package io.smanicome.yahtzee.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TwoPairCategory should")
class TwoPairCategoryShould {
    private final TwoPairCategory twoPairCategory = new TwoPairCategory();

    public static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(List.of(), 0),
                Arguments.of(List.of(1), 0),
                Arguments.of(List.of(1,1,2,2,3), 6),
                Arguments.of(List.of(1,1,2,2,3,3), 10)
        );
    }

    @ParameterizedTest
    @DisplayName("evaluate a roll that contains two pairs")
    @MethodSource("arguments")
    void evaluate(List<Integer> roll, int expectedResult) {
        final var result = twoPairCategory.evaluate(roll);

        assertEquals(expectedResult, result);
    }
}