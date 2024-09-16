package com.niantic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaggerTest
{
    private Dagger dagger;
    private Dagger noDagger;

    @BeforeEach
    public void setup()
    {
        dagger = new Dagger("The Dagger", 20, 3);
        noDagger = new Dagger("No Daggers", 20, 0);
    }

    @Test
    public void addDagger_should_increaseDaggerCount()
    {
        // arrange
        int expectedDaggerCount = 4;

        // act
        dagger.addDagger();
        int actualDaggerCount = dagger.getDaggerCount();

        // assert
        assertEquals(expectedDaggerCount, actualDaggerCount, "addDagger should increase daggerCount by 1.");
    }

    @Test
    public void daggerAttack_should_dealDefaultDamage()
    {
        int expectedDmg = 20;
        int expectedPC = 20;

        int actualDmg = dagger.attack();
        int actualPC = dagger.getPercentCharged();

        assertEquals(expectedDmg, actualDmg, "Dagger attack should deal default damage if there are daggers.");
        assertEquals(expectedPC, actualPC, "Dagger attack should add 20 to percentCharged if attack is valid.");
    }

    @Test
    public void daggerAttack_shouldNot_dealDamageIfNoDaggers()
    {
        int expectedDmg = 0;
        int expectedPC = 0;

        int actualDmg = noDagger.attack();
        int actualPC = dagger.getPercentCharged();

        assertEquals(expectedDmg, actualDmg, "Attack with no daggers deals no damage.");
        assertEquals(expectedPC, actualPC, "Attack with no daggers should not increase percentCharged.");
    }

    @Test
    public void daggerRange_shouldBe_10()
    {
        int expectedRange = 10;

        int actualRange = dagger.getRange();

        assertEquals(expectedRange, actualRange, "Dagger range should be 10.");
    }

    @Test
    public void daggerPowerAttack_should_dealCorrectDamage()
    {
        int expectedDmg1 = 20;
        int expectedDmg2 = 60;
        int expectedDmg3 = 0;

        dagger.setPercentCharged(90);
        int actualDmg1 = dagger.powerAttack();

        dagger.setPercentCharged(100);
        int actualDmg2 = dagger.powerAttack();

        noDagger.setPercentCharged(100);
        int actualDmg3 = noDagger.powerAttack();

        assertEquals(expectedDmg1, actualDmg1, "powerAttack Dagger at 90 percentCharged should return default damage.");
        assertEquals(expectedDmg2, actualDmg2, "powerAttack Dagger at 100 percentCharged should return 3x damage.");
        assertEquals(expectedDmg3, actualDmg3, "powerAttack with No daggers should return no damage.");
    }

    @Test
    public void daggerPowerAttack_should_setCorrectPercentCharged()
    {
        int expectedPC1 = 0;

        dagger.setPercentCharged(100);
        dagger.powerAttack();
        int actualPC1 = dagger.getPercentCharged();

        assertEquals(expectedPC1, actualPC1, "Dagger powerAttack should reset percentCharged to 0 if valid.");
    }

    @Test
    public void daggerPowerAttack_should_decreaseDaggerCount()
    {
        int expected = 2;

        dagger.setPercentCharged(100);
        dagger.powerAttack();
        int actual = dagger.getDaggerCount();

        assertEquals(expected, actual, "Dagger valid powerAttack should decrease dagger count by 1.");
    }
}
