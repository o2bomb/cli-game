package model;

public class DamageUltra extends Enchantment {
    public DamageUltra() {
        super();
    }

    public DamageUltra(Weapon next) {
        super(next);
    }

    @Override
    public int getEffect() {
        return next.getEffect() + 5;
    }

    @Override
    public int getCost() {
        return next.getCost() + 10;
    }
}