package view;

import java.util.*;

import controller.GoBack;

public class Menu implements MenuEntry {
    private String description;
    private ArrayList<MenuEntry> entries;

    public Menu(String description) {
        this.description = description;
        entries = new ArrayList<>();
    }

    public Menu(String description, MenuEntry prevMenu) {
        this.description = description;
        entries = new ArrayList<>();
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

    public String display() {
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

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void doAction(Scanner sc) {
        while(true) {
            System.out.println("Select an action:");
            System.out.println(display());
            try {
                int choice = Integer.parseInt(sc.nextLine());
                entries.get(choice).doAction(sc);
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
    }
}