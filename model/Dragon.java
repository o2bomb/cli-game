package model;

public class Dragon extends Enemy {
    public Dragon() {
        // DEFAULT STATS:
        // - name: Dragon
        // - maxHealth: 100
        // - currHealth: 100
        // - gold: 100
        // - probability: 0
        // - minDamage: 15
        // - maxDamage: 30
        // - minDefence: 15
        // - maxDefence: 20
        super("Dragon", 100, 100, 100, 0, 15, 30, 15, 20);
    }

    public Dragon(String name, int maxHealth, int currHealth, int gold, int probability, int minDamage, int maxDamage,
            int minDefence, int maxDefence) {
        super(name, maxHealth, currHealth, gold, probability, minDamage, maxDamage, minDefence, maxDefence);
    }

    @Override
    public int updateProbability() {
        // Do not increase probability if it takes it above 100
        if (probability + 15 > 100) {
            probability += 15;
        }
        return probability;
    }

    /**
     * There is a 35% chance of one of the following happening when it attacks:
     *  - Damage inflicted will double (25% out of 35% chance)
     *  - It will recover 10 health (10% out of 35% chance)
     */
    @Override
    public int getDamage() {
        int damage = super.getDamage();
        if(Math.random() < 0.35) {
            if(Math.random() < (25 / 35)) {
                damage *= 2; 
            } else {
                doHeal(10);
            }
        }
        return damage;
    }
}