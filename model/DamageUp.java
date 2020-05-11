package model;

public class DamageUp extends Enchantment {
    public DamageUp() {
        super();
    }

    public DamageUp(Weapon next) {
        super(next);
    }

    @Override
    public int getEffect() {
        return next.getEffect() + 2;
    }

    @Override
    public int getCost() {
        return next.getCost() + 5;
    }
}