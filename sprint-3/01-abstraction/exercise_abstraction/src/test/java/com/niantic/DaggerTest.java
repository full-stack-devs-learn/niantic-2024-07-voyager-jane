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
}
