package controller;

import java.util.Scanner;

import view.MenuEntry;

public class GoBack implements MenuEntry {
    private MenuEntry prevMenu;

    public GoBack(MenuEntry prevMenu) {
        this.prevMenu = prevMenu;
    }

    @Override
    public boolean doAction(Scanner sc) {
        return true;
    }

    @Override
    public String getDescription() {
        return "Go back";
    }
}