package com.niantic;

public class Bow extends Weapon
{


    public Bow(String name, int damage) {
        super(name, damage);
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
        return 0;
    }
}
