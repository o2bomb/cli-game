package model;

public class Fire extends Enchantment {
    public Fire() {
        super("Fire Damage");
    }

    public Fire(Weapon next) {
        super(next);
    }

    @Override
    public int getEffect() {
        int addedDamage = (int)(Math.random() * 10 + 5);
        return next.getEffect() + addedDamage;
    }

    @Override
    public int getCost() {
        return next.getCost() + 20;
    }
}