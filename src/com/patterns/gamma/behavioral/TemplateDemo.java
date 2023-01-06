package com.patterns.gamma.behavioral;

/*
* Algorithms can be decomposed into common parts and specifics. So, whilst the Strategy pattern
* does this through decomposition, the Template Method pattern does this through inheritance. It allows
* us to define the 'skeleton' of the algorithm, with concrete implementations defined in subclasses.
* */

abstract class BoardGame {
    protected int currentPlayer;
    protected final int numberOfPlayers;

    public BoardGame(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void run() {
        start();
        while (!hasWinner()) {
            takeTurn();
        }
        System.out.println("Player " + getWinningPlayer() + " wins");
    }

    // Template methods will be implemented by inheriting class

    protected abstract void start();
    protected abstract boolean hasWinner();
    protected abstract void takeTurn();
    protected abstract int getWinningPlayer();
}

class Chess extends BoardGame {
    private int maxTurns = 10;
    private int turn = 1;

    public Chess() {
        super(2);
    }

    @Override
    protected void start() {
        System.out.println("Starting a game of chess");
    }

    @Override
    protected boolean hasWinner() {
        return turn == maxTurns;
    }

    @Override
    protected void takeTurn() {
        System.out.println("Turn " + (turn++) + " taken by player " + currentPlayer);
        currentPlayer = (currentPlayer + 1) % numberOfPlayers;
    }

    @Override
    protected int getWinningPlayer() {
        return 0;
    }
}

class TemplateDemo {
    public static void main(String[] args) {
        new Chess().run();
    }
}
