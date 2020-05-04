package model;

public abstract class Potion implements Item {
    private String name;
    private int cost;
    private int minEffect;
    private int maxEffect;

    public Potion(String name, int cost, int minEffect, int maxEffect) {
        this.name = name;
        this.cost = cost;
        this.minEffect = minEffect;
        this.maxEffect = maxEffect;
    }

    @Override
    public int getEffect() {
        int effect = (int)(Math.random() * maxEffect + minEffect);
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
}