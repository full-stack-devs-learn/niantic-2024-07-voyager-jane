package com.niantic;

public class Dagger extends Weapon
{
    private int daggerCount;

    public Dagger(String name, int damage, int daggerCount) {
        super(name, damage);

        this.daggerCount = daggerCount;
    }

    public int getDaggerCount() {
        return daggerCount;
    }

    public void addDagger()
    {
        daggerCount++;
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
