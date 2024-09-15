package com.niantic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwordTest
{
    private Sword sword1;
    private Sword sword2;

    @BeforeEach
    public void setup()
    {
        sword1 = new Sword("Unbreakable Faith", 20);
    }

    @Test
    public void newSword_should_returnCorrectProperties()
    {
        // arrange
        String expectedName = "Unbreakable Faith";
        int expectedDamage = 20;
        int expectedPercentCharged = 0;

        // act
        String actualName = sword1.getName();
        int actualDamage = sword1.getDamage();
        int actualPercentCharged = sword1.getPercentCharged();

        // assert
        assertEquals(expectedName, actualName, "Name is correct");
        assertEquals(expectedDamage, actualDamage, "Damage is correct");
        assertEquals(expectedPercentCharged, actualPercentCharged, "Percent Charged is correct");
    }

    @Test
    public void swordAttack_should_returnDefaultDamage()
    {
        // arrange
        int expectedDamage = 20;

        // act
        int actualDamage = sword1.attack();

        // assert
        assertEquals(expectedDamage, actualDamage, "Attack should return 20 if sword's default damage is 20.");
    }

    @Test
    public void swordAttack_should_chargePercentChargedCorrectly()
    {
        // arrange
        int expectedCharge = 10;
        int expectedNoCharge = 100;

        // act
        sword1.attack();
        int actualCharge = sword1.getPercentCharged();

        sword1.setPercentCharged(100);
        int actualNoCharge = sword1.getPercentCharged();

        // assert
        assertEquals(expectedCharge, actualCharge, "After attack, percentCharged should charge if percentCharged < 100");
        assertEquals(expectedNoCharge, actualNoCharge, "After attack, percentCharged should not charge if percentCharged = 100");

    }
}
