package model;

public class Weapon implements WeaponInterface {
    private String name;
    private int cost;
    private int minDamage;
    private int maxDamage;
    private String type;
    private String damageType;

    public Weapon(String name, int cost, int minDamage, int maxDamage, String type, String damageType) {
        this.name = name;
        this.cost = cost;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.type = type;
        this.damageType = damageType;
    }

    @Override
    public int getEffect() {
        int effect = (int)(Math.random() * maxDamage + minDamage);
        
        return effect;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getDamageType() {
        return damageType;
    }
    
}