package model;

public class DamageUltra extends Enchantment {
    public DamageUltra() {
        super("Damage +5");
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

    @Override
    public String getDisplayName() {
        return next.getDisplayName() + "<Damage +5>";
    }
}