package controller;

import view.MenuEntry;

public class ExitGame implements MenuEntry {
    private Game game;

    public ExitGame(Game game) {
        this.game = game;
    }

    @Override
    public String getDescription() {
        return "Exit the game";
    }

    @Override
    public void doAction() {
        game.endGame();
    }
    
}