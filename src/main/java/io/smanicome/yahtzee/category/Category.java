package io.smanicome.yahtzee.category;

import java.util.List;

public interface Category {
    int evaluate(List<Integer> roll);
}
