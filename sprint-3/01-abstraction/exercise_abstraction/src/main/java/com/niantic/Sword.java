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

        System.out.println(name + " deals " + damage + " damage.");

        return damage;
    }

    @Override
    public int powerAttack() {
        if (percentCharged < 50)
        {
            return attack();
        }

        else if (percentCharged == 100)
        {
            percentCharged = 0;

            int totDmg = damage * 4;
            System.out.println(name + " deals " + totDmg + " damage.");

            return totDmg;
        }

        else
        {
            percentCharged -= 50;

            int totDmg = damage * 2;
            System.out.println(name + " deals " + totDmg + " damage.");

            return totDmg;
        }
    }

    @Override
    public int getRange() {
        return 1;
    }
}
