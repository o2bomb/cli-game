package view;

import java.util.*;

import controller.Game;
import controller.GoBack;

public class Menu implements MenuEntry {
    private String description;
    private ArrayList<MenuEntry> entries;
    private Game game;

    public Menu(String description, Game game) {
        this.description = description;
        entries = new ArrayList<>();
        this.game = game;
    }

    public Menu(String description, Game game, MenuEntry prevMenu) {
        this.description = description;
        entries = new ArrayList<>();
        this.game = game;
        entries.add(new GoBack(prevMenu));
    }

    public MenuEntry getEntry(int index) {
        return entries.get(index);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addEntry(MenuEntry e) {
        entries.add(e);
    }

    public void removeEntry(int index) {
        entries.remove(index);
    }

    /**
     * Replaces the current entry list with the new entries.
     * @param entries The new list of entries
     */
    public void refreshEntries(List<MenuEntry> newEntries) {
        ArrayList<MenuEntry> temp = new ArrayList<>();
        temp.add(this.entries.get(0));
        temp.addAll(newEntries);
        this.entries = temp;
    }

    /**
     * Generates a displayable string that represents the Menu object
     * Example:
     *  [0] Exit
     *  [1] Choice 1
     *  [2] Choice 2
     *  [3] Choice 3
     * @return
     */
    public String displayMenu() {
        String display = new String();
        for(int i = 0; i < entries.size(); i++) {
            MenuEntry e = entries.get(i);
            display += String.format("[%d] %s", i, e.getDescription());
            if(i != entries.size() - 1) {
                display += "\n";
            }
        }
        return display;
    }

    /**
     * Generates a displayable string that represents the Player object
     * @return
     */
    public String displayPlayerInfo() {
        String display = new String();
        display += "========================= PLAYER STATS =========================\n";
        display += String.format("Name: %s\n", game.getPlayer().getName());
        display += String.format("Health: %d / %d\n", game.getPlayer().getCurrHealth(), game.getPlayer().getMaxHealth());
        display += String.format("Gold: %d\n", game.getPlayer().getGold());
        display += String.format("Equipped Weapon: %s\n", game.getPlayer().getEquippedWeapon().getDisplayName());
        display += String.format("Equipped Armour: %s\n", game.getPlayer().getEquippedArmour().getDisplayName());
        display += "Healing potions: ";
        for(int i = 0; i < game.getPlayer().healingPotionCount(); i++) {
            display += String.format("%s ", new String(Character.toChars(0x2661)));
        }
        display += "\n";
        display += "Damaging potions: ";
        for(int i = 0; i < game.getPlayer().damagingPotionCount(); i++) {
            display += String.format("%s ", new String(Character.toChars(0x2620)));
        }
        display += "\n";
        display += "================================================================\n";

        return display;
    }

    /**
     * Selects the entry at the specified index
     */
    public boolean select(int index, Scanner sc) {
        return entries.get(index).doAction(sc);
    }

    /**
     * Clears the console
     */
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Display all available menu options to the player, and receive
     * player input for selecting a menu option.
     */
    @Override
    public boolean doAction(Scanner sc) {
        boolean dontRepeat = false;
        while(!dontRepeat) {
            System.out.println("Select an action:");
            System.out.println(displayMenu());
            System.out.println(displayPlayerInfo());
            try {
                int choice = Integer.parseInt(sc.nextLine());
                clearScreen();
                dontRepeat = select(choice, sc);
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Your choice does not exist.");
            } catch(NumberFormatException e) {
                System.out.println("Your choice must be a number.");
            } catch(NoSuchElementException e) {
                System.out.println("Your choice cannot be empty.");
            } catch(IllegalStateException e) {
                System.out.println("Choice scanner failed. Please try again: " + e.getMessage());
            }
        }
        return false;
    }
}