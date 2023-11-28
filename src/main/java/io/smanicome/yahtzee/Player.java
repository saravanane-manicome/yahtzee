package io.smanicome.yahtzee;

public record Player(String name, int score) {
    public Player(String name) {
        this(name, 0);
    }

    public Player withScore(int score) {
        return new Player(name, score);
    }
}
