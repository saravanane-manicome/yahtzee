package io.smanicome.yahtzee;

import io.smanicome.dice.Dice;
import io.smanicome.yahtzee.category.NKindCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("DiceBasedYahtzee should")
class DiceBasedYahtzeeShould {
    @Mock
    private Dice dice;

    @Mock
    private YahtzeeCommand yahtzeeCommand;

    @InjectMocks
    private DiceBasedYahtzee diceBasedYahtzee;

    @Test
    void returnResultOfGame() {
        Player toto = new Player("Toto");
        final var expectedResult = List.of(toto.withScore(4));

        when(dice.roll(anyInt())).thenReturn(List.of(1,1,1,2,2));
        when(yahtzeeCommand.askReroll()).thenReturn(false);
        when(yahtzeeCommand.askCategory()).thenReturn(new NKindCategory(2));

        final var result = diceBasedYahtzee.run(List.of(toto));

        assertEquals(expectedResult, result);

        final var orderVerifier = inOrder(dice, yahtzeeCommand);
        orderVerifier.verify(dice).roll(5);
        orderVerifier.verify(yahtzeeCommand).askReroll();
        orderVerifier.verify(yahtzeeCommand).askCategory();
        orderVerifier.verifyNoMoreInteractions();
    }

}