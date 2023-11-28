package io.smanicome.yahtzee.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("YahtzeeCategory should")
class YahtzeeCategoryShould {
    private final YahtzeeCategory yahtzeeCategory = new YahtzeeCategory();

    public static Stream<Arguments> evaluate() {
        return Stream.of(
                Arguments.of(List.of(), 50),
                Arguments.of(List.of(1), 50),
                Arguments.of(List.of(1,1,1,1,1), 50),
                Arguments.of(List.of(1,2,3,4,5), 0)
        );
    }

    @ParameterizedTest(name = "given {0}, expect {1}")
    @DisplayName("evaluate roll filled with the same number")
    @MethodSource("evaluate")
    void evaluate(List<Integer> roll, int expectedResult) {
        final var result = yahtzeeCategory.evaluate(roll);
        assertEquals(expectedResult, result);
    }
}