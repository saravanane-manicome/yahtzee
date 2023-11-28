package io.smanicome.yahtzee.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SpecificValueCategory should")
class SpecificValueCategoryShould {
    public static Stream<Arguments> arguments() {
        return IntStream.range(1, 7)
                .mapToObj(i -> {
                    return Stream.of(
                            Arguments.of(i, List.of(), 0),
                            Arguments.of(i, List.of(i), i),
                            Arguments.of(i, List.of(i,i,i,i,i), 5*i)
                    );
                })
                .flatMap(stream -> stream);
    }

    @ParameterizedTest(name = "with specific value {0}, given {1}, expect {2}")
    @DisplayName("evaluate roll fo a specific value")
    @MethodSource("arguments")
    void evaluate(int specificValue, List<Integer> roll, int expectedResult) {
        final var specificValueCategory = new SpecificValueCategory(specificValue);

        final var result = specificValueCategory.evaluate(roll);

        assertEquals(expectedResult, result);
    }
}