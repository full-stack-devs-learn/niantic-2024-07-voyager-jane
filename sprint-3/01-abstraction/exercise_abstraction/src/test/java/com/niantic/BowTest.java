package com.niantic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BowTest
{
    private Bow standardBow;
    private Bow poisonBow;
    private Bow explosiveBow;

    @BeforeEach
    public void setup()
    {
        standardBow = new Bow("Golden Harp", 20, "standard", 5);
        poisonBow = new Bow("Corrupt Harmony", 20, "poison", 5);
        explosiveBow = new Bow("Sonnet of Sparks", 20, "explosive", 5);
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

        Thread.sleep(5000);
        actualIncrease = standardBow.getArrowCount();

        assertEquals(expectedDecrease, actualDecrease, "Bow attack should decrease arrowCount by 1.");
        assertEquals(expectedIncrease, actualIncrease, "After a valid attack, replenish arrowCount by 1.");
    }

    @Test
    public void bowPowerAttack_should_chargePercent() throws InterruptedException {
        int expectedCharge = 100;

        standardBow.powerAttack();

        Thread.sleep(10000);
        int actualCharge = standardBow.getPercentCharged();

        assertEquals(expectedCharge, actualCharge, "Power attack should charge to 100% after 10 seconds.");
    }

    @Test
    public void bowPowerAttack_should_unleashUnlimitedArrows() throws InterruptedException {
        boolean expectedMoreDoubleDmg;

        int actualDmg = standardBow.powerAttack();

        Thread.sleep(15000);
        expectedMoreDoubleDmg = actualDmg > 40;

        assertTrue(expectedMoreDoubleDmg, "powerAttack should release 2x default damage with each unlimited arrow for over 5 seconds.");
    }
}
