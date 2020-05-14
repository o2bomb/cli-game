package controller;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import model.Armour;
import model.Player;
import view.Menu;
import view.MenuEntry;

public class ChooseArmour extends Menu implements PlayerObserver {
    private Player player;

    public ChooseArmour(Game game, Menu prevMenu) {
        super("Choose armour", game, prevMenu);
        this.player = game.getPlayer();
        player.addObserver(this);
        for(Armour a : player.getArmours()) {
            addEntry(new ChooseArmourEntry(a));
        }
    }

    @Override
    public void playerUpdated() {
        List<MenuEntry> newEntries = new ArrayList<>();
        for(Armour a : player.getArmours()) {
            newEntries.add(new ChooseArmourEntry(a));
        }
        refreshEntries(newEntries);
    }

    private class ChooseArmourEntry implements MenuEntry {
        private Armour armour;

        public ChooseArmourEntry(Armour armour) {
            this.armour = armour;
        }

        @Override
        public String getDescription() {
            return armour.getDisplayName();
        }

        @Override
        public void doAction(Scanner sc) {
            try {
                player.selectArmour(armour);
            } catch (NoSuchElementException e) {
                System.out.println("Armour could not be selected: " + e.getMessage());
            }
        }

    }

}