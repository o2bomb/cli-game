package controller;

import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Player;
import model.Weapon;
import view.Menu;
import view.MenuEntry;

public class ChooseWeapon extends Menu {
    private Player player;

    public ChooseWeapon(Player player, Menu prevMenu) {
        super("Choose weapon", prevMenu);
        this.player = player;
        for(Weapon w : player.getWeapons()) {
            addEntry(new ChooseWeaponEntry(w));
        }
    }

    private class ChooseWeaponEntry implements MenuEntry {
        private Weapon weapon;

        public ChooseWeaponEntry(Weapon weapon) {
            this.weapon = weapon;
        }

        @Override
        public String getDescription() {
            return weapon.getDisplayName();
        }

        @Override
        public void doAction(Scanner sc) {
            try {
                player.selectWeapon(weapon);
            } catch (NoSuchElementException e) {
                System.out.println("Weapon could not be selected: " + e.getMessage());
            }
        }
    }
}