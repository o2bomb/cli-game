package model;

public class DamageUp extends Enchantment {
    public DamageUp(Weapon next) {
        super(next);
    }

    @Override
    public int getEffect() {
        return next.getEffect() + 2;
    }
}