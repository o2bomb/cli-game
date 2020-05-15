package model;

import java.util.*;

import javax.naming.InsufficientResourcesException;

import controller.PlayerObserver;

public class Player extends Character {
	private List<Item> inventory;
	private Weapon equippedWeapon;
	private Armour equippedArmour;
	private Set<PlayerObserver> observers;

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
		inventory = new LinkedList<>();
		equippedWeapon = new BaseWeapon("Wooden sword", 5, 3, 5, "Sword", "Poking");
		equippedArmour = new Armour("T-shirt", 5, "Cloth", 2, 5);
		observers = new HashSet<>();
	}
	
    public Player(String name, int maxHealth, int currHealth, int gold, Weapon weapon, Armour armour) throws NullPointerException {
		super(name, maxHealth, currHealth, gold);
		inventory = new LinkedList<>();
		// Throws NullPointerException if weapon or armour is null
		equippedWeapon = Objects.requireNonNull(weapon);
		equippedArmour = Objects.requireNonNull(armour);
		observers = new HashSet<>();
	}
	
	public List<Item> getInventory() {
		return inventory;
	}

	/**
	 * Gets all the weapons currently in the player's inventory (excluding equipped)
	 * @return Weapons from the player's inventory
	 */
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

	/**
	 * Gets all the armours currently in the player's inventory (excluding equipped)
	 * @return Armour from the player's inventory
	 */
	public List<Armour> getArmours() {
		List<Armour> armours = new LinkedList<>();
		for(Item i : inventory) {
			if(i instanceof Armour) {
				Armour a = (Armour)i;
				armours.add(a);
			}
		}
		return armours;
	}

	/**
	 * Gets all the potions currently in the player's inventory
	 * @return Potions from the player's inventory
	 */
	public List<Potion> getPotions() {
		List<Potion> potions = new LinkedList<>();
		for(Item i : inventory) {
			if(i instanceof Potion) {
				Potion p = (Potion)i;
				potions.add(p);
			}
		}
		return potions;
	}

	public int damagingPotionCount() {
		int count = 0;
		for(Item i : inventory) {
			if(i instanceof Potion) {
				Potion p = (Potion)i;
				if(p.getType() == 'd') {
					count++;
				}
			}
		}
		return count;
	}

	public int healingPotionCount() {
		int count = 0;
		for(Item i : inventory) {
			if(i instanceof Potion) {
				Potion p = (Potion)i;
				if(p.getType() == 'h') {
					count++;
				}
			}
		}
		return count;
	}

	public Weapon getEquippedWeapon() {
		return equippedWeapon;
	}

	public Armour getEquippedArmour() {
		return equippedArmour;
	}

	public void addItem(Item item) {
		inventory.add(item);
		notifyObservers();
	}

	public void removeItem(Item item) {
		inventory.remove(item);
		notifyObservers();
	}

	public void addEnchantmentToEquippedWeapon(Enchantment enchantment) {
		enchantment.setNext(equippedWeapon);
		Weapon enchantedWeapon = enchantment;
		this.equippedWeapon = enchantedWeapon;
		notifyObservers();
	}

	public void payGold(int amount) throws InsufficientResourcesException {
		if(getGold() - amount < 0) {
			throw new InsufficientResourcesException("Player does not have enough gold");
		}
		minusGold(amount);
		notifyObservers();
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
		notifyObservers();
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
		notifyObservers();
	}

	/**
	 * Add a PlayerObserver
	 * @param ob The player observer
	 */
	public void addObserver(PlayerObserver ob) {
		observers.add(ob);
	}

	/**
	 * Calls all observers observing this player object
	 */
	public void notifyObservers() {
		for(PlayerObserver ob : observers) {
			ob.playerUpdated();
		}
	}

	/**
	 * Perform a defend based on equipped armour's defence stats
	 */
	@Override
	public int doDefend(int damage) {
		int defence = equippedArmour.getEffect();
        int damageDealt = Math.max(0, damage - defence);

		currHealth = Math.max(0, currHealth - damageDealt);
		notifyObservers();
		return damageDealt;
	}

	/**
	 * Get player's damage based on equipped weapon's attack stats
	 */
	@Override
	public int getDamage() {
		return equippedWeapon.getEffect();
	} 
}