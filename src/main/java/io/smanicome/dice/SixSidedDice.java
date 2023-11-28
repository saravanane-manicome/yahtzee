package io.smanicome.dice;

import java.util.List;
import java.util.Random;

public class SixSidedDice implements Dice {
    private final Random random;

    public SixSidedDice(Random random) {
        this.random = random;
    }

    @Override
    public List<Integer> roll(int amountOfRolls) {
        if(amountOfRolls < 0)
            throw new IllegalArgumentException("amountOfRolls must be 0 or more");

        return random.ints(amountOfRolls, 1, 7)
                .boxed()
                .toList();
    }
}
