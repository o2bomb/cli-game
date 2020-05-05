package model;

public class Slime extends Enemy {
    public Slime() {
        // DEFAULT STATS:
        // - name: Slime
        // - maxHealth: 10
        // - currHealth: 10
        // - gold: 10
        // - probability: 50
        // - minDamage: 3
        // - maxDamage: 5
        // - minDefence: 0
        // - maxDefence: 2
        super("Slime", 10, 10, 10, 50, 3, 5, 0, 2);
    }

    public Slime(String name, int maxHealth, int currHealth, int gold, int probability, int minDamage, int maxDamage,
            int minDefence, int maxDefence) {
        super(name, maxHealth, currHealth, gold, probability, minDamage, maxDamage, minDefence, maxDefence);
    }

    @Override
    public void doDefend(int damage) {
        // TODO Auto-generated method stub
    }

    /**
     * There is a 20% chance that the attack will miss (have no damage)
     */
    @Override
    public int getDamage() {
        int damage = super.getDamage();
        if(Math.random() < 0.2) {
            damage = 0;
        }
        return damage;
    }
}