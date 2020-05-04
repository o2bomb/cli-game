package model;

public abstract class Character {
    private String name;
    private int maxHealth;
    private int currHealth;
    private int gold;

    public Character(String name, int maxHealth, int currHealth, int gold) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currHealth = currHealth;
        this.gold = gold;
    }

    public abstract void doDefend(int damage);

    public abstract int getDamage();
}