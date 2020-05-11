package model;

public class PowerUp extends Enchantment {
    public PowerUp(Weapon next) {
        super(next);
    }

    @Override
    public int getEffect() {
        return (int)(next.getEffect() * 1.1);
    }

    @Override
    public int getCost() {
        return next.getCost() + 10;
    }
}