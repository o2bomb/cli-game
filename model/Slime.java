package model;

public class Slime extends Enemy {
    public Slime(String name, int maxHealth, int currHealth, int gold, int probability) {
        super(name, maxHealth, currHealth, gold, probability);
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