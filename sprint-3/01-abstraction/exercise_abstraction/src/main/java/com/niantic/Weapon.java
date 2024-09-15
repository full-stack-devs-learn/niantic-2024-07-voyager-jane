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
        if (percentCharged < 0)
        {
            this.percentCharged = 0;
        }
        else if (percentCharged > 100)
        {
            this.percentCharged = 100;
        }
        else
        {
            this.percentCharged = percentCharged;
        }
    }

    public abstract int attack();

    public abstract int powerAttack();

    public abstract int getRange();
}
