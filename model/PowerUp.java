package model;

public class PowerUp extends Enchantment {
    public PowerUp() {
        super("Power-Up");
    }

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

    @Override
    public String getDisplayName() {
        return next.getDisplayName() + "<Power-Up>";
    }
}