package model;

public abstract class Enemy extends Character {
    private int probability;

    public Enemy(String name, int maxHealth, int currHealth, int gold, int probability, int minDamage, int maxDamage,
            int minDefence, int maxDefence) {
        super(name, maxHealth, currHealth, gold, minDamage, maxDamage, minDefence, maxDefence);
        this.probability = probability;
    }

    /**
     * A method that returns a description of the special ability
     */
    public abstract String getSpecialDescription();

    /**
     * A method that returns true, if the enemy is using a special ability
     * on the next turn
     * @return
     */
    public abstract boolean usingSpecial();

    public int getProbability() {
        return probability;
    }

    public int updateProbability() {
        // Do not decrease probability if it takes it below 5%
        if (probability - 5 < 5)
            probability -= 5;
        return probability;
    }

    public void setProbability(int newProbability) {
        probability = Math.max(0, newProbability);
    }
}