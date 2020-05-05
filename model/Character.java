package model;

public abstract class Character {
    private String name;
    private int maxHealth;
    protected int currHealth;
    private int gold;
    private int minDamage;
    private int maxDamage;
    private int minDefence;
    private int maxDefence;

    public Character(String name, int maxHealth, int currHealth, int gold, int minDamage, int maxDamage, int minDefence, int maxDefence) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currHealth = currHealth;
        this.gold = gold;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.minDefence = minDefence;
        this.maxDefence = maxDefence;
    }

    public Character(String name, int maxHealth, int currHealth, int gold) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currHealth = currHealth;
        this.gold = gold;
        this.minDamage = 0;
        this.maxDamage = 0;
        this.minDefence = 0;
        this.maxDefence = 0;
    }

    public void doDefend(int damage) {
        int defence = (int)(Math.random() * maxDefence + minDefence);
        int damageDealt = Math.max(0, damage - defence);

        currHealth = Math.max(0, currHealth - damageDealt);
    }

    public void doHeal(int amount) {
        currHealth += amount;
        // If current health exceeds max health, just set it to max health
        if(currHealth > maxHealth) {
            currHealth = maxHealth;
        }
    }
    
    public int getDamage() {
        int damage = (int)(Math.random() * maxDamage + minDamage);

        return damage;
    }

    public String getName() {
        return name;
    }

    public int getGold() {
        return gold;
    }

    public int getCurrHealth() {
        return currHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}