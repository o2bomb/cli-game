package model;

public class DamageUltra extends Enchantment {
    public DamageUltra(Weapon next) {
        super(next);
    }

    @Override
    public int getEffect() {
        return next.getEffect() + 5;
    }
}