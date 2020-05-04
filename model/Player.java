package model;

import java.util.*;

public class Player extends Character {
    private List<Item> inventory;


	public Player(String name, int maxHealth, int currHealth, int gold) {
		super(name, maxHealth, currHealth, gold);
        inventory = new LinkedList<Item>();
    }

	@Override
	public void doDefend(int damage) {
		// TODO Auto-generated method stub
        
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}
}