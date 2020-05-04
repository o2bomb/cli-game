package model;

public abstract class Enemy extends Character {
    private int probability = 0;

    public Enemy(String name, int maxHealth, int currHealth, int gold, int probability) {
        super(name, maxHealth, currHealth, gold);
        this.probability = probability;
    }

    public int getProbability() {
        // Do not decrease probability if it takes it below 5%
        if(probability-5 < 5)
            probability -= 5;
        return probability;
    }
}