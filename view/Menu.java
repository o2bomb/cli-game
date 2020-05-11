package view;

import java.util.*;

public class Menu implements MenuEntry {
    private String description;
    private ArrayList<MenuEntry> entries;

    public Menu(String description) {
        this.description = description;
        entries = new ArrayList<>();
    }

    public Menu(String description, Menu prevMenu) {
        this.description = description;
        entries = new ArrayList<>();
        prevMenu.setDescription("Go back");
        entries.add(prevMenu);
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
            display += String.format("[%d] %s\n", i, e.getDescription());
        }
        return display;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void doAction() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("Select an action:");
                System.out.println(display());
                int choice = sc.nextInt();
                entries.get(choice).doAction();
                break;
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Your choice does not exist: " + e.getMessage());
            } catch(InputMismatchException e) {
                System.out.println("Your choice must be a number: " + e.getMessage());
            } catch(NoSuchElementException e) {
                System.out.println("Your name cannot be empty: " + e.getMessage());
            } catch(IllegalStateException e) {
                System.out.println("Choice scanner failed. Please try again: " + e.getMessage());
            }
        }
        sc.close();
    }
}