package model;

import java.lang.Character;

public class Potion implements Item {
    private String name;
    private char type;
    private int cost;
    private int minEffect;
    private int maxEffect;

    public Potion(String name, int cost, int minEffect, int maxEffect, char type) {
        this.name = name;
        this.type = Character.toLowerCase(type);
        this.cost = cost;
        this.minEffect = minEffect;
        this.maxEffect = maxEffect;
    }

    /**
     * Returns the type of potion (Damaging/Healing)
     * @return The type of the potion. Is always a lowercase letter
     */
    public char getType() {
        return type;
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

    @Override
    public String getDisplayName() {
        String typeName = "Mundane";
        if(type == 'h' ) {
            typeName = "Healing";
        } else if(type == 'd') {
            typeName = "Damaging";
        }

        return String.format("%s [%s]", name, typeName);
    }
}