package io.smanicome.yahtzee;

import io.smanicome.yahtzee.category.Category;
import io.smanicome.yahtzee.exception.ImpossibleCommandException;

import java.util.List;

public interface YahtzeeCommand {

    boolean askReroll() throws ImpossibleCommandException;
    void notifyRoll(List<Integer> roll);

    Category askCategory() throws ImpossibleCommandException;
}
