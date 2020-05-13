package controller;

import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Player;
import view.MenuEntry;
import view.Menu;

public class ChoosePlayerName implements MenuEntry {
    private Player player;
    private Menu prevMenu;

    public ChoosePlayerName (Player player, Menu prevMenu) {
        this.player = player;
        this.prevMenu = prevMenu;
    }

    @Override
    public void doAction() {
        
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Choose a name for your character: ");
            try {
                String name = sc.nextLine();
                player.setName(name);
                sc.close();
                break;
            } catch(NoSuchElementException e) {
                System.out.println("Your name cannot be empty.");
            } catch(IllegalStateException e) {
                System.out.println("Name scanner failed. Please try again: " + e.getMessage());
            }
        }
        prevMenu.doAction();
    }

    @Override
    public String getDescription() {
        return "Choose character name";
    }
    
}