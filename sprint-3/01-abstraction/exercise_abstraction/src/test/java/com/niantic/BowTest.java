package com.niantic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
