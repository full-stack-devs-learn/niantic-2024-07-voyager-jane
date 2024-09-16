package com.niantic;

public class Bow extends Weapon
{
    private String arrowType;
    private int quiverSize;
    private int arrowCount;

    public Bow(String name, int damage, String arrowType, int quiverSize) {
        super(name, damage);

        this.arrowType = arrowType;
        this.quiverSize = quiverSize;
        this.arrowCount = quiverSize;
    }

    public String getArrowType() {
        return arrowType;
    }

    public int getQuiverSize() {
        return quiverSize;
    }

    public int getArrowCount() {
        return arrowCount;
    }

    @Override
    public int attack() {
        return 0;
    }

    @Override
    public int powerAttack() {
        return 0;
    }

    @Override
    public int getRange() {
        return 20;
    }
}
