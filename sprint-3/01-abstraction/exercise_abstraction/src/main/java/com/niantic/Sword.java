package com.niantic;

public class Sword extends Weapon
{
    public Sword(String name, int damage)
    {
        super(name, damage);
    }

    @Override
    public int attack() {
        if (percentCharged != 100)
        {
            percentCharged += 10;
        }

        System.out.println(getName() + " deals " + getDamage() + " damage.");

        return getDamage();
    }

    @Override
    public int powerAttack() {
        return 0;
    }

    @Override
    public int getRange() {
        return 0;
    }
}
