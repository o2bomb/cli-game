package controller;

import java.util.NoSuchElementException;

import model.Armour;
import model.Player;
import view.Menu;
import view.MenuEntry;

public class ChooseArmour extends Menu {
    private Player player;

    public ChooseArmour(Player player, Menu prevMenu) {
        super("Choose armour", prevMenu);
        this.player = player;
        for(Armour a : player.getArmours()) {
            addEntry(new ChooseArmourEntry(a));
        }
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
        public void doAction() {
            try {
                player.selectArmour(armour);
            } catch (NoSuchElementException e) {
                System.out.println("Armour could not be selected: " + e.getMessage());
            }
        }

    }
}