package model;

import java.util.*;

import javax.naming.InsufficientResourcesException;

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
		equippedWeapon = new BaseWeapon("Wooden sword", 5, 3, 5, "poking", "Sword");
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

	public List<Weapon> getWeapons() {
		List<Weapon> weapons = new LinkedList<>();
		for(Item i : inventory) {
			if(i instanceof Weapon) {
				Weapon w = (Weapon)i;
				weapons.add(w);
			}
		}

		return weapons;
	}

	public List<Armour> getArmours() {
		List<Armour> armours = new LinkedList<>();
		for(Item i : inventory) {
			if(i instanceof Armour) {
				Armour w = (Armour)i;
				armours.add(w);
			}
		}

		return armours;
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

	public void addEnchantmentToEquippedWeapon(Enchantment enchantment) {
		enchantment.setNext(equippedWeapon);
		Weapon enchantedWeapon = enchantment;
		this.equippedWeapon = enchantedWeapon;
	}

	public void payGold(int amount) throws InsufficientResourcesException {
		if(getGold() - amount < 0) {
			throw new InsufficientResourcesException("Player does not have enough gold");
		}
		minusGold(amount);
	}

	/**
	 * Replace the currently equipped weapon with the indicated weapon. If
	 * the weapon does not exist within the inventory, throw an exception. Else,
	 * put the currently equpped weapon into the inventory and equip the new weapon
	 */
	public void selectWeapon(Weapon weapon) throws NoSuchElementException {
		if(!inventory.contains(weapon)) {
			throw new NoSuchElementException();
		}
		inventory.add(equippedWeapon);
		inventory.remove(weapon);
		equippedWeapon = weapon;
	}

	/**
	 * Replace the currently equipped armour with the indicated armour. If
	 * the armour does not exist within the inventory, throw an exception. Else,
	 * put the currently equpped armour into the inventory and equip the new armour
	 */
	public void selectArmour(Armour armour) throws NoSuchElementException {
		if(!inventory.contains(armour)) {
			throw new NoSuchElementException();
		}
		inventory.add(equippedArmour);
		inventory.remove(armour);
		equippedArmour = armour;
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