package com.niantic;

import java.util.Timer;

public class Bow extends Weapon
{
    private String arrowType;
    private int quiverSize;
    private int arrowCount;
    private Timer arrowTimer;
    private Timer chargeTimer;
    private Timer unlimitedArrows;

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
        if (arrowCount != 0)
        {
            arrowCount--;

            if ("standard".equals(this.arrowType.toLowerCase()))
            {
                System.out.println("Standard arrow deals " + damage + " damage!");
                return damage;
            }

            if ("poison".equals(this.arrowType.toLowerCase()))
            {
                int totDmg = damage * 2;

                System.out.println("Poison arrow deals " + totDmg + " damage!");

                return totDmg;
            }

            if ("explosive".equals(this.arrowType.toLowerCase()))
            {
                int totDmg = damage * 3;

                System.out.println("Explosive arrow deals " + totDmg + " damage!");

                return totDmg;
            }
        }

        System.out.println("There are no arrows so no damage dealt!");
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
