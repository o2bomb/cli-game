package model;

public class Armour implements Item {
    private String name;
    private int cost;
    private int minDefence;
    private int maxDefence;
    private String material;

    public Armour(String name, int cost, String material, int minDefence, int maxDefence) {
        this.name = name;
        this.cost = cost;
        this.minDefence = minDefence;
        this.maxDefence = maxDefence;
        this.material = material;
    }

    @Override
    public int getEffect() {
        int defence = (int)(Math.random() * maxDefence + minDefence);
        return defence;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCost() {
        return cost;
    }

    public String getMaterial() {
        return material;
    }
}