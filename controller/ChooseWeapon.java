package controller;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import model.Player;
import model.Weapon;
import view.Menu;
import view.MenuEntry;

public class ChooseWeapon extends Menu implements PlayerObserver {
    private Player player;

    public ChooseWeapon(Game game, Menu prevMenu) {
        super("Choose weapon", game, prevMenu);
        this.player = game.getPlayer();
        player.addObserver(this);
        for(Weapon w : player.getWeapons()) {
            addEntry(new ChooseWeaponEntry(w));
        }
    }

    @Override
    public void playerUpdated() {
        List<MenuEntry> newEntries = new ArrayList<>();
        for(Weapon w : player.getWeapons()) {
            newEntries.add(new ChooseWeaponEntry(w));
        }
        refreshEntries(newEntries);
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