package io.smanicome.yahtzee.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("NKindCategory should")
class NKindCategoryShould {

    public static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(2, List.of(), 0),
                Arguments.of(2, List.of(1), 0),
                Arguments.of(2, List.of(1,2,3,4,5), 0),
                Arguments.of(2, List.of(1,1,2,2,3), 4),

                Arguments.of(3, List.of(), 0),
                Arguments.of(3, List.of(1), 0),
                Arguments.of(3, List.of(1,2,3,4,5), 0),
                Arguments.of(3, List.of(1,1,3,3,3), 9),

                Arguments.of(4, List.of(), 0),
                Arguments.of(4, List.of(1), 0),
                Arguments.of(4, List.of(1,2,3,4,5), 0),
                Arguments.of(4, List.of(1,4,4,4,4), 16)
        );
    }

    @ParameterizedTest(name = "for {0} kind, given {1}, expect {2}")
    @DisplayName("evaluate a roll with n identical numbers")
    @MethodSource("arguments")
    void evaluate(int kind, List<Integer> roll, int expectedResult) {
        final var nKindCategory = new NKindCategory(kind);

        final var result = nKindCategory.evaluate(roll);

        assertEquals(expectedResult, result);
    }
}