package model;

public abstract class Enemy extends Character {
    protected int probability;

    public Enemy(String name, int maxHealth, int currHealth, int gold, int probability, int minDamage, int maxDamage,
            int minDefence, int maxDefence) {
        super(name, maxHealth, currHealth, gold, minDamage, maxDamage, minDefence, maxDefence);
        this.probability = probability;
    }

    public int updateProbability() {
        // Do not decrease probability if it takes it below 5%
        if (probability - 5 < 5)
            probability -= 5;
        return probability;
    }
}