package model;

public abstract class Enchantment implements Weapon {
    protected Weapon next;

    public Enchantment(Weapon next) {
        this.next = next;
    }

    @Override
    public int getEffect() {
        return next.getEffect();
    }

    @Override
    public String getName() {
        return next.getName();
    }

    @Override
    public int getCost() {
        return next.getCost();
    }

    @Override
    public String getType() {
        return next.getType();
    }

    @Override
    public String getDamageType() {
        return next.getDamageType();
    }

    @Override
    public String getDisplayName() {
        return next.getDisplayName();
    }
}