package controller;

import view.MenuEntry;

public class GoBack implements MenuEntry {
    private MenuEntry prevMenu;

    public GoBack(MenuEntry prevMenu) {
        this.prevMenu = prevMenu;
    }

    @Override
    public void doAction() {
        prevMenu.doAction();
    }

    @Override
    public String getDescription() {
        return "Go back";
    }
}