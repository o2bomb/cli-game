package view;

import java.util.*;

public class Menu implements MenuEntry {
    private String description;
    private ArrayList<MenuEntry> entries;

    public Menu(String description) {
        this.description = description;
        entries = new ArrayList<>();
    }

    public MenuEntry getEntry(int index) {
        return entries.get(index);
    }

    public void addEntry(MenuEntry e) {
        entries.add(e);
    }

    public void removeEntry(int index) {
        entries.remove(index);
    }

    public void select(int index) {
        entries.get(index).doAction();
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
        System.out.println(display());
    }
}