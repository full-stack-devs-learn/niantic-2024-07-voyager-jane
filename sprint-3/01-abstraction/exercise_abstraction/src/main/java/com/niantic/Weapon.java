package com.niantic;

public abstract class Weapon
{
    protected String name;
    protected int damage;
    protected int percentCharged;

    public Weapon(String name, int damage)
    {
        this.name = name;
        this.damage = damage;
        this.percentCharged = 0;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getPercentCharged() {
        return percentCharged;
    }

    protected void setPercentCharged(int percentCharged) {
        this.percentCharged = percentCharged;
    }

    public abstract int attack();

    public abstract int powerAttack();

    public abstract int getRange();
}
