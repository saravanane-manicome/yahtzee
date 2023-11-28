package io.smanicome.yahtzee;

import io.smanicome.yahtzee.category.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("IOStreamYahtzeeCommand should")
class IOStreamYahtzeeCommandShould {

    @ParameterizedTest
    @DisplayName("return false when user refuses reroll")
    @CsvSource(value = {"N", "''", "azerty"}, emptyValue = "''")
    void shouldReturnFalseOnRerollNegativeAnswer(String input) {
        final var inputStream = new ByteArrayInputStream(input.getBytes());
        final var yahtzeeCommand = new IOStreamYahtzeeCommand(inputStream, OutputStream.nullOutputStream());
        final var result = yahtzeeCommand.askReroll();
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueOnRerollY() {
        final var inputStream = new ByteArrayInputStream("Y".getBytes());
        final var yahtzeeCommand = new IOStreamYahtzeeCommand(inputStream, OutputStream.nullOutputStream());
        final var result = yahtzeeCommand.askReroll();
        assertTrue(result);
    }

    @Test
    void shouldPrintRoll() throws IOException {
        final var outputStream = new ByteArrayOutputStream();
        final var yahtzeeCommand = new IOStreamYahtzeeCommand(InputStream.nullInputStream(), outputStream);

        yahtzeeCommand.notifyRoll(List.of(1,2,3,4,5,6));

        assertEquals("1, 2, 3, 4, 5, 6" + System.lineSeparator(), outputStream.toString());
    }

    public static Stream<Arguments> askCategoryArguments() {
        return Stream.of(
                Arguments.of("1", new SpecificValueCategory(1)),
                Arguments.of("2", new SpecificValueCategory(2)),
                Arguments.of("3", new SpecificValueCategory(3)),
                Arguments.of("4", new SpecificValueCategory(4)),
                Arguments.of("5", new SpecificValueCategory(5)),
                Arguments.of("6", new SpecificValueCategory(6)),
                Arguments.of("2K", new NKindCategory(2)),
                Arguments.of("3K", new NKindCategory(3)),
                Arguments.of("4K", new NKindCategory(4)),
                Arguments.of("2P", new TwoPairCategory()),
                Arguments.of("SS", new SmallStraightCategory()),
                Arguments.of("LS", new LargeStraightCategory()),
                Arguments.of("FH", new FullHouseCategory()),
                Arguments.of("Y", new YahtzeeCategory()),
                Arguments.of("C", new ChanceCategory())
        );
    }

    @ParameterizedTest
    @DisplayName("return category for input")
    @MethodSource("askCategoryArguments")
    void askCategory(String input, Category expectedResult) {
        final var inputStream = new ByteArrayInputStream(input.getBytes());
        final var yahtzeeCommand = new IOStreamYahtzeeCommand(inputStream, OutputStream.nullOutputStream());

        final var result = yahtzeeCommand.askCategory();

        assertEquals(expectedResult, result);
    }
}