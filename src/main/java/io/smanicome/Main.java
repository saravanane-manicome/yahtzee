package io.smanicome;

import io.smanicome.dice.SixSidedDice;
import io.smanicome.yahtzee.DiceBasedYahtzee;
import io.smanicome.yahtzee.IOStreamYahtzeeCommand;
import io.smanicome.yahtzee.Player;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final var command = new IOStreamYahtzeeCommand(System.in, System.out);
        final var dice = new SixSidedDice(new Random(System.currentTimeMillis()));
        final var yahtzee = new DiceBasedYahtzee(dice, command);

        final var result = yahtzee.run(List.of(new Player("Toto"), new Player("Titi")));

        System.out.println(result);
    }
}