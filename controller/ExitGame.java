package controller;

import java.util.Scanner;

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
    public boolean doAction(Scanner sc) {
        game.endGame();
        return true;
    }
    
}