package model;

public class Ogre extends Enemy {
    public Ogre() {
        // DEFAULT STATS:
        // - name: Ogre
        // - maxHealth: 40
        // - currHealth: 40
        // - gold: 40
        // - probability: 20
        // - minDamage: 5
        // - maxDamage: 10
        // - minDefence: 6
        // - maxDefence: 12
        super("Ogre", 40, 40, 40, 20, 5, 10, 6, 12);
    }

    public Ogre(String name, int maxHealth, int currHealth, int gold, int probability, int minDamage, int maxDamage,
            int minDefence, int maxDefence) {
        super(name, maxHealth, currHealth, gold, probability, minDamage, maxDamage, minDefence, maxDefence);
    }

    @Override
    public String getSpecialDescription() {
        return "20% chance for two attacks in a row";
    }

    /**
     * There is a 20% chance that it will attack twice in a row
     */
    @Override
    public int getDamage() {
        int damage = super.getDamage();
        if(usingSpecial()) {
            damage += super.getDamage();
        }
        return damage;
    }

    @Override
    public boolean usingSpecial() {
        if(Math.random() < 0.2) {
            return true;
        }
        return false;
    }
}