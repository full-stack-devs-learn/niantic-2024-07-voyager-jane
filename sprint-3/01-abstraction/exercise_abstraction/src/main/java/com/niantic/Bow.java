package com.niantic;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Bow extends Weapon
{
    private String arrowType;
    private int quiverSize;
    private int arrowCount;
    private Timer arrowTimer;
    private Timer chargeTimer;
    private Timer unlimitedArrows;
    private TimerTask arrowTask;
    private TimerTask chargeTask;
    private TimerTask unlimitedTask;
    private int powerDmg = 0;
    boolean stopPower = true;

    public Bow(String name, int damage, String arrowType, int quiverSize) {
        super(name, damage);

        this.arrowType = arrowType;
        this.quiverSize = quiverSize;
        this.arrowCount = quiverSize;

        arrowTimer = new Timer();
        unlimitedArrows = new Timer();
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

            arrowTask = new TimerTask() {
                @Override
                public void run() {
                    if (arrowCount < quiverSize)
                    {
                        arrowCount++;
                        System.out.println("After 5 seconds, one arrow has been replenished. " + arrowCount + " arrows available.");
                    }
                }
            };
            arrowTimer.schedule(arrowTask, 5000);

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
        CountDownLatch latch = new CountDownLatch(1);

        chargeTimer = new Timer();
        chargeTask = new TimerTask() {
            @Override
            public void run() {
                if (percentCharged < 100)
                {
                    System.out.println("Charging ... " + percentCharged + "%");
                    percentCharged += 10;
                }
                else
                {
                    System.out.println("100% charged! Unlimited arrows about to be unleashed.");
                    latch.countDown();
                }
            }
        };
        chargeTimer.schedule(chargeTask, 0, 2000);

        try {
            latch.await(22, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        chargeTimer.cancel();

        unlimitedArrows = new Timer();
        unlimitedTask = new TimerTask() {
            @Override
            public void run() {
                if (stopPower)
                {
                    powerDmg += damage * 2;
                }
            }
        };
        unlimitedArrows.schedule(unlimitedTask, 0, 1000);

        CountDownLatch secondLatch = new CountDownLatch(1);
        Timer cancelPower = new Timer();
        TimerTask cancelTask =  new TimerTask() {
            @Override
            public void run() {
                stopPower = false;
                unlimitedArrows.cancel();
                secondLatch.countDown();
            }
        };
        cancelPower.schedule(cancelTask, 5000);

        try {
            secondLatch.await(6, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        cancelPower.cancel();

        System.out.println("Unlimited Arrows have done " + powerDmg + " damage!");

        percentCharged = 0;

        return powerDmg;
    }

    @Override
    public int getRange() {
        return 20;
    }
}
