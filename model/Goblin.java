package model;

public class Goblin extends Enemy {
    public Goblin() {
        // DEFAULT STATS:
        // - name: Goblin
        // - maxHealth: 30
        // - currHealth: 30
        // - gold: 20
        // - probability: 30
        // - minDamage: 3
        // - maxDamage: 8
        // - minDefence: 4
        // - maxDefence: 8
        super("Goblin", 30, 30, 20, 30, 3, 8, 4, 8);
    }

    public Goblin(String name, int maxHealth, int currHealth, int gold, int probability, int minDamage, int maxDamage,
            int minDefence, int maxDefence) {
        super(name, maxHealth, currHealth, gold, probability, minDamage, maxDamage, minDefence, maxDefence);
    }

    /**
     * There is a 50% that the attack will have 3 extra damage
     */
    @Override
    public int getDamage() {
        int damage = super.getDamage();
        if(Math.random() < 0.5) {
            damage += 3;
        }
        return damage;
    }
}