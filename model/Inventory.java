package model;

import java.util.*;

public class Inventory {
    private List<Weapon> weapons;
    private List<Armour> armours;
    private List<Potion> potions;

    public Inventory() {
        this.weapons = new LinkedList<>();
        this.armours = new LinkedList<>();
        this.potions = new LinkedList<>();
    }

    public List<Item> getInventory() {
        List<Item> inventory = new LinkedList<>();
        inventory.addAll(potions);
        inventory.addAll(weapons);
        inventory.addAll(armours);

        return inventory;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public List<Armour> getArmours() {
        return armours;
    }

    public List<Potion> getPotions() {
        return potions;
    }

    public void addItem(Weapon weapon) {
        weapons.add(weapon);
    }

    public void addItem(Armour armour) {
        armours.add(armour);
    }

    public void addItem(Potion potion) {
        potions.add(potion);
    }
}