package io.smanicome.yahtzee;

import io.smanicome.dice.Dice;

import java.util.Collections;
import java.util.List;

public class DiceBasedYahtzee implements Yahtzee {
    private final Dice dice;
    private final YahtzeeCommand command;

    public DiceBasedYahtzee(Dice dice, YahtzeeCommand command) {
        this.dice = dice;
        this.command = command;
    }

    public List<Player> run(List<Player> players) {
        return players.stream()
            .map(this::play)
            .toList();
    }

    private Player play(Player player) {
        final var roll = roll();
        final var category = command.askCategory();
        final var score = category.evaluate(roll);

        return player.withScore(score);
    }

    private List<Integer> roll() {
        var roll = Collections.<Integer>emptyList();

        for (int i = 0; i < 3; i++) {
            roll = dice.roll(5);

            command.notifyRoll(roll);

            if(!command.askReroll()) {
                break;
            }
        }

        return roll;
    }
}
