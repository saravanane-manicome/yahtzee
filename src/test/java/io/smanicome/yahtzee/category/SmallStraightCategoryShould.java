package io.smanicome.yahtzee.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("SmallStraightCategory should")
class SmallStraightCategoryShould {
    private final SmallStraightCategory smallStraightCategory = new SmallStraightCategory();

    public static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(List.of(), 0),
                Arguments.of(List.of(1), 0),
                Arguments.of(List.of(1,1,2,2,3,3), 0),
                Arguments.of(List.of(1,2,3,4,5), 15)
        );
    }

    @ParameterizedTest(name = "given {0}, expect {1}")
    @DisplayName("evaluate roll as a small straight")
    @MethodSource("arguments")
    void evaluate(List<Integer> roll, int expectedResult) {
        final var result = smallStraightCategory.evaluate(roll);

        assertEquals(expectedResult, result);
    }
}