package com.niantic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowTest
{
    private Bow standardBow;
    private Bow poisonBow;
    private Bow explosiveBow;
    private Timer timer;
    private TimerTask task;

    @BeforeEach
    public void setup()
    {
        standardBow = new Bow("Golden Harp", 20, "standard", 5);
        poisonBow = new Bow("Corrupt Harmony", 20, "poison", 5);
        explosiveBow = new Bow("Sonnet of Sparks", 20, "explosive", 5);

        timer = new Timer();
    }

    @Test
    public void bowRange_shouldBe_20()
    {
        int expected = 20;

        int actual = standardBow.getRange();

        assertEquals(expected, actual, "Bow range should be 20.");
    }

    @Test
    public void bowAttack_should_dealDamageBasedOnArrowType()
    {
        int expectedStandard = standardBow.getDamage();
        int expectedPoison = poisonBow.getDamage() * 2;
        int expectedExplosive = explosiveBow.getDamage() * 3;

        int actualStandard = standardBow.attack();
        int actualPoison = poisonBow.attack();
        int actualExplosive = explosiveBow.attack();

        assertEquals(expectedStandard, actualStandard, "Standard bows should deal default damage.");
        assertEquals(expectedPoison, actualPoison, "Poison bows should deal 2x default damage.");
        assertEquals(expectedExplosive, actualExplosive, "Explosive bows should deal 3x default damage.");
    }

    @Test
    public void bowAttack_should_changeArrowCountCorrectly() throws InterruptedException {
        int expectedDecrease = standardBow.getQuiverSize() - 1;
        int expectedIncrease = standardBow.getQuiverSize();

        standardBow.attack();
        int actualDecrease = standardBow.getArrowCount();
        int actualIncrease;

        // testing timertask and making sure arrowCount replenishes after 5 seconds
        CountDownLatch latch = new CountDownLatch(1);
        task = new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
            }
        };
        timer.schedule(task, 7000);
        latch.await(10, TimeUnit.SECONDS);

        actualIncrease = standardBow.getArrowCount();

        assertEquals(expectedDecrease, actualDecrease, "Bow attack should decrease arrowCount by 1.");
        assertEquals(expectedIncrease, actualIncrease, "After a valid attack, replenish arrowCount by 1.");
    }

    @Test
    public void bowPowerAttack_should_chargePercent() throws InterruptedException {
        int expectedCharge = 100;

        standardBow.powerAttack();

        CountDownLatch latch = new CountDownLatch(1);
        task = new TimerTask() {
            @Override
            public void run() {
                latch.countDown();
            }
        };
        timer.schedule(task, 11000);
        latch.await(13, TimeUnit.SECONDS);

        int actualCharge = standardBow.getPercentCharged();

        assertEquals(expectedCharge, actualCharge, "Power attack should charge to 100% after 10 seconds.");
    }
}
