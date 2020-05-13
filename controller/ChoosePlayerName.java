package controller;

import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Player;
import view.MenuEntry;

public class ChoosePlayerName implements MenuEntry {
    private Player player;

    public ChoosePlayerName (Player player) {
        this.player = player;
    }

    @Override
    public void doAction(Scanner sc) {
        
        while(true) {
            System.out.print("Choose a name for your character: ");
            try {
                String name = sc.nextLine();
                player.setName(name);
                break;
            } catch(NoSuchElementException e) {
                System.out.println("Your name cannot be empty.");
            }
        }
    }

    @Override
    public String getDescription() {
        return "Choose character name";
    }
    
}