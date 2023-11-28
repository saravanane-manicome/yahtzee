package io.smanicome.yahtzee.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("FullHouseCategory should")
class FullHouseCategoryShould {
    private final FullHouseCategory fullHouseCategory = new FullHouseCategory();

    public static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(List.of(), 0),
                Arguments.of(List.of(1), 0),
                Arguments.of(List.of(1,2,3,4,5), 0),
                Arguments.of(List.of(1,1,3,3,3), 11)
        );
    }

    @ParameterizedTest(name = "given {0}, expect {1}")
    @DisplayName("evaluate a full house")
    @MethodSource("arguments")
    void evaluate(List<Integer> roll, int expectedResult) {
        final var result = fullHouseCategory.evaluate(roll);
        assertEquals(expectedResult, result);
    }
}