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
    public void doAction() {
        Scanner sc = new Scanner(System.in);

        while(true) {
            try {
                System.out.print("Choose a name for your character: ");
                String name = sc.nextLine();
                player.setName(name);
                break;
            } catch(NoSuchElementException e) {
                System.out.println("Your name cannot be empty: " + e.getMessage());

            } catch(IllegalStateException e) {
                System.out.println("Name scanner failed. Please try again: " + e.getMessage());
            }
        }
        sc.close();
    }

    @Override
    public String getDescription() {
        return "Choose character name";
    }
    
}