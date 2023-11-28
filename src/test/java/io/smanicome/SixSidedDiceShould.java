package io.smanicome;

import io.smanicome.dice.SixSidedDice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("SixSidedDice should")
class SixSidedDiceShould {
    @Mock
    private Random random;

    @InjectMocks
    private SixSidedDice sixSidedDiceRoll;

    @Test
    @DisplayName("return a list of six random integers")
    void returnListOfRandomInts() {
        when(random.ints(anyLong(), anyInt(), anyInt())).thenReturn(IntStream.of(1, 2, 3, 4, 5, 6));

        final var result = sixSidedDiceRoll.roll(6);

        final var expected = List.of(1, 2, 3, 4, 5, 6);

        assertEquals(expected, result);

        verify(random).ints(6, 1, 7);
        verifyNoMoreInteractions(random);
    }

    @Test
    @DisplayName("thrown on negative amount of rolls")
    void throwOnNegativeAmountOfRolls() {
        assertThrows(IllegalArgumentException.class, () -> sixSidedDiceRoll.roll(-1));
    }
}