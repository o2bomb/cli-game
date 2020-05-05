package model;

import java.util.*;

public class Player extends Character {
	private List<Item> inventory;
	private Weapon equippedWeapon;
	private Armour equippedArmour;

	public Player(String name) {
		// DEFAULT STATS:
        // - name: <player input>
        // - maxHealth: 30
        // - currHealth: 30
        // - gold: 100
        // - minDamage: <from starter weapon>
        // - maxDamage: <from starter weapon>
        // - minDefence: <from starter armour>
		// - maxDefence: <from starter armour>
		super(name, 30, 30, 100);
		inventory = new LinkedList<Item>();
		equippedWeapon = new Weapon("Wooden sword", 5, 3, 5, "poking", "Sword");
		equippedArmour = new Armour("T-shirt", 5, "Cloth", 2, 5);
	}
	
    public Player(String name, int maxHealth, int currHealth, int gold, Weapon weapon, Armour armour) throws NullPointerException {
		super(name, maxHealth, currHealth, gold);
		inventory = new LinkedList<Item>();
		// Throws NullPointerException if weapon or armour is null
		equippedWeapon = Objects.requireNonNull(weapon);
		equippedArmour = Objects.requireNonNull(armour);
	}
	
	public List<Item> getInventory() {
		return inventory;
	}

	public Weapon getEquippedWeapon() {
		return equippedWeapon;
	}

	public Armour getEquippedArmour() {
		return equippedArmour;
	}

	public void addItem(Item item) {
		inventory.add(item);
	}

	/**
	 * Perform a defend based on equipped armour's defence stats
	 */
	@Override
	public void doDefend(int damage) {
		int defence = equippedArmour.getEffect();
        int damageDealt = Math.max(0, damage - defence);

        currHealth = Math.max(0, currHealth - damageDealt);
	}

	/**
	 * Get player's damage based on equipped weapon's attack stats
	 */
	@Override
	public int getDamage() {
		return equippedWeapon.getEffect();
	} 
}