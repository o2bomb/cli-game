package model;

public class Fire extends Enchantment {
    public Fire(Weapon next) {
        super(next);
    }

    @Override
    public int getEffect() {
        int addedDamage = (int)(Math.random() * 10 + 5);
        return next.getEffect() + addedDamage;
    }
}